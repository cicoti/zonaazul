package br.com.zonaazul.delegate;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.zonaazul.dto.Vaga;
import br.com.zonaazul.facade.VagaFacade;
import br.com.zonaazul.util.BusinessServiceException;
import br.com.zonaazul.util.Haversine;
import br.com.zonaazul.util.ServiceException;

public class VagaDelegate implements Serializable { 
	
	private static final long serialVersionUID = -5916801404195287097L;
	@Inject private VagaFacade vagaFacade;

	public boolean pesquisarVagaExiste(Vaga v) throws ServiceException{
		List<Vaga> listaVaga = this.listaVaga();
		for(Vaga vaga : listaVaga){
			if(vaga.getNoVaga().equals(v.getNoVaga())){
				return true;
			}
		}
		
		throw new BusinessServiceException("A vaga solicitada n�o existe.");
		
	}
	
	public boolean pesquisarVagaLivre(Vaga v) throws ServiceException{
		List<Vaga> listaVagaLivre = this.listaVagaLivre();
		for(Vaga vaga : listaVagaLivre){
			if(vaga.getNoVaga().equals(v.getNoVaga())){
				return true;
			}
		}
		
		throw new BusinessServiceException("A vaga solicitada n�o est� livre.");
		
	}
	
	public boolean pesquisarVagaProximidade(Vaga v) throws ServiceException {
		List<Vaga> listaVagaLivre = this.listaVagaLivre();
		for(Vaga vaga : listaVagaLivre){
			if(vaga.getNoVaga().equals(v.getNoVaga())){

				//Haversine.distance(startLat, startLong, endLat, endLong)
				int distancia = (int) (Haversine.distance(Double.parseDouble(v.getNrLatitude().replaceAll(",", ".")), Double.parseDouble(v.getNrLongitude().replaceAll(",", ".")), Double.parseDouble(vaga.getNrLatitude().replaceAll(",", ".")), Double.parseDouble(vaga.getNrLongitude().replaceAll(",", "."))));
							
				System.out.println("Distancia :" + distancia);
				
				if(distancia > 100){
					throw new BusinessServiceException("Voc� est� distante da vaga solicitada.");
				} else {
					return true;
				}
								
			}
		}
		
		return false;
	}
	
	public Vaga buscarVaga(Vaga v) throws ServiceException{
		List<Vaga> listaVaga = this.listaVaga();
		for(Vaga vaga : listaVaga){
			if(vaga.getNoVaga().equals(v.getNoVaga())){
				return vaga;
			}
		}
		
		throw new BusinessServiceException("A vaga solicitada n�o foi encontrada.");
		
	}
	

	private List<Vaga> listaVagaLivre() throws ServiceException {
		return vagaFacade.listaVagaLivre();
	}
	
	private List<Vaga> listaVaga() throws ServiceException {
		return vagaFacade.listaVaga();
	}

}

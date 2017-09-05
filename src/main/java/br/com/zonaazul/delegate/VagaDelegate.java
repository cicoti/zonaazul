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
		
		throw new BusinessServiceException("A vaga solicitada não existe.");
		
	}
	
	public boolean pesquisarVagaLivre(Vaga v) throws ServiceException{
		List<Vaga> listaVagaLivre = this.listaVagaLivre();
		for(Vaga vaga : listaVagaLivre){
			if(vaga.getNoVaga().equals(v.getNoVaga())){
				return true;
			}
		}
		
		throw new BusinessServiceException("A vaga solicitada não está livre.");
		
	}
	
	public Vaga pesquisarVagaProximidade(Vaga v) throws ServiceException {
		List<Vaga> listaVagaLivre = this.listaVagaLivre();
		for(Vaga vaga : listaVagaLivre){
			if(vaga.getNoVaga().equals(v.getNoVaga())){
				
				System.out.println("Longitude GPS " + v.getNrLongitude());
				System.out.println("Latitude GPS " + v.getNrLatitude());

				System.out.println("Longitude Vaga Solicitada "+ vaga.getNrLongitude());
				System.out.println("Latitude Vaga Solicitada " + vaga.getNrLatitude());

				//Haversine.distance(startLat, startLong, endLat, endLong)
				int distancia = (int) (Haversine.distance(Double.parseDouble(v.getNrLatitude().replaceAll(",", ".")), Double.parseDouble(v.getNrLongitude().replaceAll(",", ".")), Double.parseDouble(vaga.getNrLatitude().replaceAll(",", ".")), Double.parseDouble(vaga.getNrLongitude().replaceAll(",", "."))));
				
				System.out.println("Distancia: " + distancia);
				
				if(distancia > 50){
					throw new BusinessServiceException("Você está distante da vaga solicitada.");
				} else {
					return v;
				}
								
				
			}
		}
		
		return null;
	}
	

	private List<Vaga> listaVagaLivre() throws ServiceException {
		return vagaFacade.listaVagaLivre();
	}
	
	private List<Vaga> listaVaga() throws ServiceException {
		return vagaFacade.listaVaga();
	}

}

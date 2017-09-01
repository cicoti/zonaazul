package br.com.zonaazul.delegate;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.zonaazul.dto.Vaga;
import br.com.zonaazul.facade.VagaFacade;
import br.com.zonaazul.util.BusinessServiceException;
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
				System.out.println("Solicitada latitude " + v.getNrLatitude());
				System.out.println("Solicitada longitude " + v.getNrLongitude());
				System.out.println("Efetiva latitude " + vaga.getNrLatitude());
				System.out.println("Efetiva longitude "+ vaga.getNrLongitude());
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

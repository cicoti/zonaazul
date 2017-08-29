package br.com.zonaazul.facade;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.zonaazul.dto.Vaga;
import br.com.zonaazul.service.VagaService;
import br.com.zonaazul.util.ServiceException;


public class VagaFacade implements Serializable {

	private static final long serialVersionUID = 374187132122344663L;
	
	@Inject private VagaService vagaService;

	public List<Vaga> listaVagaLivre() throws ServiceException {
		return vagaService.listaVagaLivre();
	}
	
	public List<Vaga> listaVaga() throws ServiceException {
		return vagaService.listaVaga();
	}
	
}

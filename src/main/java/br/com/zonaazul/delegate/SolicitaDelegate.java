package br.com.zonaazul.delegate;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.zonaazul.dto.Solicita;
import br.com.zonaazul.service.SolicitaService;
import br.com.zonaazul.util.ServiceException;


public class SolicitaDelegate implements Serializable {

	private static final long serialVersionUID = 374187132122344663L;
	
	@Inject private SolicitaService solicitaService;

	public void efetivar(Solicita solicita) throws ServiceException{
		solicitaService.efetivar(solicita);
	}
	
}

package br.com.zonaazul.facade;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.zonaazul.dto.Credito;
import br.com.zonaazul.service.CreditoService;
import br.com.zonaazul.util.ServiceException;


public class CreditoFacade implements Serializable {

	private static final long serialVersionUID = -3906493223300729750L;
	
	@Inject private CreditoService creditoService;

	public Credito buscarCredito() throws ServiceException {
		return creditoService.buscarCredito();
	}
	
}

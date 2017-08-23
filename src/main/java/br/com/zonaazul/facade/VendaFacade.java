package br.com.zonaazul.facade;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.zonaazul.dto.Usuario;
import br.com.zonaazul.service.AutenticaService;
import br.com.zonaazul.service.VendaService;
import br.com.zonaazul.util.ServiceException;


public class VendaFacade implements Serializable {

	private static final long serialVersionUID = 2292561051916535975L;
	
	@Inject private VendaService vendaService;

	public Long saldoVenda(Usuario usuario) throws ServiceException {
		return vendaService.saldo(usuario);
	}
	
}

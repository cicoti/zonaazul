package br.com.zonaazul.facade;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.zonaazul.dto.Usuario;
import br.com.zonaazul.service.CompraService;
import br.com.zonaazul.util.ServiceException;


public class CompraFacade implements Serializable {

	private static final long serialVersionUID = -158246731744377790L;
	
	@Inject private CompraService compraService;

	public Long saldoCompra(Usuario usuario) throws ServiceException {
		return compraService.saldo(usuario);
	}
	
}

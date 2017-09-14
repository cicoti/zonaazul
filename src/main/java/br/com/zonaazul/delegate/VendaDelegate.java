package br.com.zonaazul.delegate;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.zonaazul.dto.Usuario;
import br.com.zonaazul.dto.Venda;
import br.com.zonaazul.service.VendaService;
import br.com.zonaazul.util.ServiceException;


public class VendaDelegate implements Serializable {

	private static final long serialVersionUID = 2292561051916535975L;
	
	@Inject private VendaService vendaService;

	public Long saldoVenda(Usuario usuario) throws ServiceException {
		return vendaService.saldo(usuario);
	}
	
	public void efetivar(Venda venda) throws ServiceException {
		 vendaService.efetivar(venda);
	}
 	
}

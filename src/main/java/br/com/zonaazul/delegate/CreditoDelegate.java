package br.com.zonaazul.delegate;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.zonaazul.dto.Usuario;
import br.com.zonaazul.facade.CompraFacade;
import br.com.zonaazul.facade.VendaFacade;
import br.com.zonaazul.util.BusinessServiceException;
import br.com.zonaazul.util.ServiceException;

public class CreditoDelegate implements Serializable { 

	private static final long serialVersionUID = 5747992233236931016L;
	
	@Inject private CompraFacade compraFacade;
	@Inject private VendaFacade vendaFacade;

	public Long buscarSaldo(Usuario usuario) throws ServiceException{
		
		Long compra = compraFacade.saldoCompra(usuario);
		Long venda = vendaFacade.saldoVenda(usuario);
		
		return (compra - venda);
		
	}
	
	public void saldoPositivo(Usuario usuario) throws ServiceException{
		if(this.buscarSaldo(usuario)<=0){
			throw new BusinessServiceException("Saldo insuficiente.");
		}
		
	}

}

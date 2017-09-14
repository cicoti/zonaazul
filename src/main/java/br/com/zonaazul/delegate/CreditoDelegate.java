package br.com.zonaazul.delegate;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.zonaazul.dto.Credito;
import br.com.zonaazul.dto.Usuario;
import br.com.zonaazul.service.CreditoService;
import br.com.zonaazul.util.BusinessServiceException;
import br.com.zonaazul.util.ServiceException;

public class CreditoDelegate implements Serializable { 

	private static final long serialVersionUID = 5747992233236931016L;
	
	@Inject private CompraDelegate compraDelegate;
	@Inject private VendaDelegate vendaDelegate;
	@Inject private CreditoService creditoService;

	public Long buscarSaldo(Usuario usuario) throws ServiceException{
		
		Long compra = compraDelegate.saldoCompra(usuario);
		Long venda = vendaDelegate.saldoVenda(usuario);
		
		return (compra - venda);
		
	}
	
	public void saldoPositivo(Usuario usuario) throws ServiceException{
		if(this.buscarSaldo(usuario)<=0){
			throw new BusinessServiceException("Saldo insuficiente.");
		}
		
	}


	public Credito buscarCredito() throws ServiceException {
		//Retorna o valor de cada credito.
		return creditoService.buscarCredito();
	}

}

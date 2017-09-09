package br.com.zonaazul.control;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.zonaazul.delegate.CreditoDelegate;
import br.com.zonaazul.dto.Credito;
import br.com.zonaazul.dto.Usuario;
import br.com.zonaazul.facade.CreditoFacade;
import br.com.zonaazul.util.BusinessServiceException;
import br.com.zonaazul.util.RuntimeServiceException;
import br.com.zonaazul.util.ServiceException;

@Named
@RequestScoped
public class CreditoBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 529995973316030320L;
	
	private Long saldo = 0L;
	private Usuario usuario;
	@Inject private CreditoDelegate creditoDelegate;
	@Inject private CreditoFacade creditoFacade;
	
	@PostConstruct
	public void init() {
		usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		try {
			this.setSaldo(this.creditoDelegate.buscarSaldo(usuario));
		} catch (BusinessServiceException e) {
			alertaMensagem(e.getMessage(),""); 
		} catch (RuntimeServiceException e) {
			erroMensagem(e.getMessage(),""); 
		} catch (Exception e){
			erroMensagem("Não foi possível recuperar o saldo, tente mais tarde.",""); 
		} 
		
	}
	
	public Double valorCredito() throws ServiceException{
		Credito credito = null;
		try {
			 credito = creditoFacade.buscarCredito();
		} catch (BusinessServiceException e) {
			alertaMensagem(e.getMessage(),""); 
		} catch (RuntimeServiceException e) {
			erroMensagem(e.getMessage(),""); 
		} catch (Exception e){
			erroMensagem("Não foi possível recuperar o credito, tente mais tarde.",""); 
		} 
		
		return credito.getVlCredito();
	}
			
	public Long getSaldo() {
		return saldo;
	}

	public void setSaldo(Long saldo) {
		this.saldo = saldo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}

package br.com.zonaazul.control;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.zonaazul.dto.Usuario;
import br.com.zonaazul.facade.CompraFacade;
import br.com.zonaazul.facade.VendaFacade;
import br.com.zonaazul.util.BusinessServiceException;
import br.com.zonaazul.util.RuntimeServiceException;

@Named
@SessionScoped
public class CreditoBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 529995973316030320L;
	
	private Long saldo;
	private Usuario usuario;
	@Inject private CompraFacade compraFacade;
	@Inject private VendaFacade vendaFacade;
	
	@PostConstruct
	public void init() {
		usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		try {
			 this.setSaldo(compraFacade.saldoCompra(usuario) - vendaFacade.saldoVenda(usuario));
		} catch (BusinessServiceException e) {
			alertaMensagem(e.getMessage(),""); 
		} catch (RuntimeServiceException e) {
			erroMensagem(e.getMessage(),""); 
		} catch (Exception e){
			erroMensagem("Não foi possível recuperar o credito, tente mais tarde.",""); 
		} 
		
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

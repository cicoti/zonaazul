package br.com.zonaazul.control;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.zonaazul.dto.Usuario;
import br.com.zonaazul.facade.CompraFacade;
import br.com.zonaazul.util.BusinessServiceException;
import br.com.zonaazul.util.RuntimeServiceException;

@Named
@SessionScoped
public class CreditoBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 529995973316030320L;
	
	private Long saldo = 0L;
	private Usuario usuario;
	@Inject private CompraFacade compraFacade;
	
	@PostConstruct
	public void init() {
		usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		
		try {
			saldo = compraFacade.saldoCompra(usuario);
		} catch (BusinessServiceException e) {
			alertaMensagem(e.getMessage(),""); 
		} catch (RuntimeServiceException e) {
			erroMensagem(e.getMessage(),""); 
		} catch (Exception e){
			erroMensagem("Não foi possível recuperar o saldo, tente mais tarde.",""); 
		} 
		
	}
		
	/*public String autenticar()  {

				try {
					Usuario usuario = autenticaFacade.pesquisarUsuario(this.usuario);
					return "principal";
				} catch (BusinessServiceException e) {
					alertaMensagem(e.getMessage(),""); 
				} catch (RuntimeServiceException e) {
					erroMensagem(e.getMessage(),""); 
				} catch (Exception e){
					erroMensagem("Não foi possível fazer a autenticação, tente mais tarde.",""); 
				} 
				
				return null;

    }*/

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

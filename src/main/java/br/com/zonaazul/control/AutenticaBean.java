package br.com.zonaazul.control;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.zonaazul.dto.Usuario;
import br.com.zonaazul.facade.AutenticaFacade;
import br.com.zonaazul.util.BusinessServiceException;
import br.com.zonaazul.util.RuntimeServiceException;

@Named
@SessionScoped
public class AutenticaBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 529995973316030320L;

	private Usuario usuario;
	
	@Inject private AutenticaFacade autenticaFacade;
	
	@PostConstruct
	public void init() {
		usuario = new Usuario();
		usuario.setEmail("silvio.cicoti@gmail.com");
		usuario.setSenha("lunaduna");
	}
		
	public String autenticar()  {

				try {
					Usuario usuario = autenticaFacade.pesquisarUsuario(this.usuario);
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuario);
					return "solicitavaga";
				} catch (BusinessServiceException e) {
					alertaMensagem(e.getMessage(),""); 
				} catch (RuntimeServiceException e) {
					erroMensagem(e.getMessage(),""); 
				} catch (Exception e){
					erroMensagem("Não foi possível fazer a autenticação, tente mais tarde.",""); 
				} 
				
				return null;

    }


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}   
	
	
	
	
}

package br.com.zonaazul.control;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.zonaazul.delegate.AutenticaDelegate;
import br.com.zonaazul.dto.Usuario;
import br.com.zonaazul.util.BusinessServiceException;
import br.com.zonaazul.util.RuntimeServiceException;

@Named
@RequestScoped
public class AutenticaBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 529995973316030320L;

	private Usuario usuario;
	
	@Inject private AutenticaDelegate autenticaFacade;
	
	@PostConstruct
	public void init() {
		usuario = new Usuario();
		usuario.setEmail("silvio.cicoti@gmail.com");
		usuario.setSenha("lunaduna");
	}
		
	public String autenticar()  {

		/*ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, String> map = ec.getRequestParameterMap();
		String email = map.get("email");
		String senha = map.get("senha");
		
		this.usuario = new Usuario();
		this.usuario.setEmail(email);
		this.usuario.setSenha(senha);*/
		
				try {
					Usuario usuario = autenticaFacade.pesquisarUsuario(this.usuario);
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuario);
					return "solicitaVaga";
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

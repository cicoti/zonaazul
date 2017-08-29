package br.com.zonaazul.control;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.zonaazul.delegate.VagaDelegate;
import br.com.zonaazul.dto.Placa;
import br.com.zonaazul.dto.Usuario;
import br.com.zonaazul.dto.Vaga;
import br.com.zonaazul.facade.AutenticaFacade;
import br.com.zonaazul.facade.PlacaFacade;
import br.com.zonaazul.util.BusinessServiceException;
import br.com.zonaazul.util.RuntimeServiceException;
import br.com.zonaazul.util.ServiceException;

@Named
@SessionScoped
public class SolicitaBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = -8937557876759194012L;

	private Usuario usuario;
	private Placa placa;
	private Vaga vaga;
		
	@Inject VagaDelegate vagaDelegate;
	@Inject PlacaFacade placaFacade;
	
	@PostConstruct
	public void init() {
		usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		placa = new Placa();
		placa.setIdUsuario(usuario.getId());
		vaga = new Vaga();
				
	}
		
	public String solicitar()  {
		
		try {
			System.out.println("Latitude: "  + this.vaga.getNrLatitude());
			vagaDelegate.pesquisarVagaExiste(this.vaga);
			vagaDelegate.pesquisarVagaLivre(this.vaga);
			placaFacade.pesquisarPlacaUsuario(this.placa);
			
		} catch (BusinessServiceException e) {
			alertaMensagem(e.getMessage(),""); 
		} catch (RuntimeServiceException e) {
			erroMensagem(e.getMessage(),""); 
		} catch (Exception e){
			erroMensagem("Não foi possível a solicitação da vaga, tente mais tarde.",""); 
		} 
		
		return null;

    }

	public Placa getPlaca() {
		return placa;
	}

	public void setPlaca(Placa placa) {
		this.placa = placa;
	}

	public Vaga getVaga() {
		return vaga;
	}

	public void setVaga(Vaga vaga) {
		this.vaga = vaga;
	}

	
	
	
}

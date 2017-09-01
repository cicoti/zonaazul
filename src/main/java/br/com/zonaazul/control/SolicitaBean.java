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
import br.com.zonaazul.facade.PlacaFacade;
import br.com.zonaazul.util.BusinessServiceException;
import br.com.zonaazul.util.RuntimeServiceException;

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
        
       /* ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        
        Map<String, String> map = ec.getRequestParameterMap();

		String longitude = map.get("longitude");
		String latitude = map.get("latitude");
		
		System.out.println(longitude + " - " + latitude);*/
		

		try {

			//System.out.println(this.vaga.getNrLatitude());
			//System.out.println(this.vaga.getNrLongitude());
			
			vagaDelegate.pesquisarVagaExiste(this.vaga);
			vagaDelegate.pesquisarVagaLivre(this.vaga);
			vagaDelegate.pesquisarVagaProximidade(this.vaga);
			
			placaFacade.pesquisarPlacaUsuario(this.placa);
			
		} catch (BusinessServiceException e) {
			alertaMensagem(e.getMessage(),""); 
		} catch (RuntimeServiceException e) {
			erroMensagem(e.getMessage(),""); 
		} catch (Exception e){
			erroMensagem("N�o foi poss�vel a solicita��o da vaga, tente mais tarde.",""); 
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

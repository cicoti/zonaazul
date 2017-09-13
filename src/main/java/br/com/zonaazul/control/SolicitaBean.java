package br.com.zonaazul.control;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.zonaazul.delegate.CreditoDelegate;
import br.com.zonaazul.delegate.VagaDelegate;
import br.com.zonaazul.dto.Credito;
import br.com.zonaazul.dto.Placa;
import br.com.zonaazul.dto.Solicita;
import br.com.zonaazul.dto.Usuario;
import br.com.zonaazul.dto.Vaga;
import br.com.zonaazul.dto.Venda;
import br.com.zonaazul.facade.CreditoFacade;
import br.com.zonaazul.facade.PlacaFacade;
import br.com.zonaazul.facade.SolicitaFacade;
import br.com.zonaazul.facade.VendaFacade;
import br.com.zonaazul.util.BusinessServiceException;
import br.com.zonaazul.util.RuntimeServiceException;

@Named
@RequestScoped
public class SolicitaBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = -8937557876759194012L;

	private Usuario usuario;
	private Placa placa;
	private Vaga vaga;
	private Credito credito;
	private Solicita solicita;
	
	@Inject SolicitaFacade solicitaFacade;
	
	@Inject VagaDelegate vagaDelegate;
	@Inject PlacaFacade placaFacade;
	@Inject VendaFacade vendaFacade;
	@Inject CreditoDelegate creditoDelegate;
	@Inject CreditoFacade creditoFacade;
	
	
	@PostConstruct
	public void init() {
		solicita = (Solicita) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("solicita");
		usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		placa = new Placa();
		placa.setIdUsuario(usuario.getIdUsuario());
		vaga = new Vaga();
	}
	
	public String solicitar()  {

		try {
			
			creditoDelegate.saldoPositivo(usuario);
			
			vagaDelegate.pesquisarVagaExiste(this.vaga);
			vagaDelegate.pesquisarVagaLivre(this.vaga);
			vagaDelegate.pesquisarVagaProximidade(this.vaga);
			
			vaga = vagaDelegate.buscarVaga(this.vaga);
			
			placa = placaFacade.pesquisarPlacaUsuario(this.placa);
			
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("vaga", vaga);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("placa", placa);
			
			credito = creditoFacade.buscarCredito();
					
			Venda venda = new Venda();
			venda.setCredito(credito);
			venda.setUsuario(usuario);
			
			vendaFacade.efetivar(venda);
						
			solicita = new Solicita();
			solicita.setPlaca(placa);
			solicita.setVaga(vaga);
			solicita.setUsuario(usuario);
			solicita.setBlExtensao(0);
			solicita.setBlNegado(0);
			solicita.setDsMotivo("");
			
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			solicita.setDtInicio(df.format(new Date()).toString());
			solicita.setDtFim(df.format(new Date().getTime() + 60 * 60 * 1000).toString());

			solicitaFacade.efetivar(solicita);
			
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("solicita", solicita);
					
			return "finalizaSolicitacao";
			
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

	public Solicita getSolicita() {
		return solicita;
	}

	public void setSolicita(Solicita solicita) {
		this.solicita = solicita;
	}

	
	
	
}

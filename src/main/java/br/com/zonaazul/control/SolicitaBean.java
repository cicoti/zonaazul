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
import br.com.zonaazul.delegate.PlacaDelegate;
import br.com.zonaazul.delegate.SolicitaDelegate;
import br.com.zonaazul.delegate.VagaDelegate;
import br.com.zonaazul.delegate.VendaDelegate;
import br.com.zonaazul.dto.Credito;
import br.com.zonaazul.dto.Placa;
import br.com.zonaazul.dto.Solicita;
import br.com.zonaazul.dto.Usuario;
import br.com.zonaazul.dto.Vaga;
import br.com.zonaazul.dto.Venda;
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
	
	@Inject SolicitaDelegate solicitaDelegate;
	
	@Inject VagaDelegate vagaDelegate;
	@Inject PlacaDelegate placaDelegate;
	@Inject VendaDelegate vendaDelegate;
	@Inject CreditoDelegate creditoDelegate;

	
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
			
			//Verifica se o saldo do compra - venda � positivo.
			creditoDelegate.saldoPositivo(usuario);
			
			//Verifica se a vaga digitada � existente.
			vagaDelegate.pesquisarVagaExiste(this.vaga);
			
			//Verifica se a vaga est� livre.
			vagaDelegate.pesquisarVagaLivre(this.vaga);
			
			//Verfica se o usuario est� proximo da vaga.
			vagaDelegate.pesquisarVagaProximidade(this.vaga);
						
			vaga = vagaDelegate.buscarVaga(this.vaga);
			
			//Primeiro saber se a placa � do usuario
			placa = placaDelegate.pesquisarPlacaUsuario(this.placa);
			
			//Depois saber se essa placa j� n�o est� estacionada. Essa ordem nao pode mudar.
			solicitaDelegate.pesquisarSolicitacaoPorPlaca(placa);
			
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("vaga", vaga);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("placa", placa);
			
			//Buscar o valor do credito/hora da vaga.
			credito = creditoDelegate.buscarCredito();
					
			Venda venda = new Venda();
			venda.setCredito(credito);
			venda.setUsuario(usuario);
			
			//Gravar venda.
			vendaDelegate.efetivar(venda);
						
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

			//Gravar solicitacao.
			solicitaDelegate.efetivar(solicita);
			
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("solicita", solicita);
					
			return "finalizaSolicitacao";
			
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

	public Solicita getSolicita() {
		return solicita;
	}

	public void setSolicita(Solicita solicita) {
		this.solicita = solicita;
	}

	
	
	
}

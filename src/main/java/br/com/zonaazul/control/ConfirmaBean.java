package br.com.zonaazul.control;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.com.zonaazul.dto.Periodo;
import br.com.zonaazul.dto.Placa;
import br.com.zonaazul.dto.Usuario;
import br.com.zonaazul.dto.Vaga;

@Named
@SessionScoped
public class ConfirmaBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = -3331938150688431861L;
	
	private Usuario usuario;
	private Placa placa;
	private Vaga vaga;
	private Periodo periodo;
	
	@PostConstruct
	public void init() {
		this.setUsuario((Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario"));
		this.setPlaca((Placa) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("placa"));
		this.setVaga((Vaga) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("vaga"));
		periodo = new Periodo();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		periodo.setInicio(df.format(new Date()).toString());
		periodo.setFim(df.format(new Date().getTime() + 60 * 60 * 1000).toString());
	}
	
	public String confirmar(){
		
		return null;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}
	
	
	
	

	
	
	
}

package br.com.zonaazul.control;

import java.io.Serializable;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import br.com.zonaazul.util.Mensagem;


public class AbstractBean implements Serializable {
	private static final String KEEP_DIALOG_OPENED = "KEEP_DIALOG_OPENED";

	public AbstractBean() {
		super();
	}

	protected void infoMensagem(String msg,String detalhe) {
		Mensagem mensagem = new Mensagem();
		mensagem.infoMensagem(msg,detalhe);
	}

	protected void alertaMensagem(String msg, String detalhe) {
		Mensagem mensagem = new Mensagem();
		mensagem.alertaMensagem(msg,detalhe);
	}
	
	protected void erroMensagem(String msg, String detalhe) {
		Mensagem mensagem = new Mensagem();
		mensagem.erroMensagem(msg,detalhe);
	}

	protected void closeDialog(){
		getRequestContext().addCallbackParam(KEEP_DIALOG_OPENED, false);
	}

	protected void keepDialogOpen(){
		getRequestContext().addCallbackParam(KEEP_DIALOG_OPENED, true);
	}

	protected RequestContext getRequestContext(){
		return RequestContext.getCurrentInstance();
	}

	public String resetAllBeans() {
		final Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

		for (String key: sessionMap.keySet()) {
			if (key.contains("|ManagedBean|")) {
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(key, null);
			}
		}

		return "inicio";
	}

	public boolean isSomenteLeitura() {
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String s = (String)request.getSession().getAttribute("somenteLeitura");
		if(s == null || s.equals("N")) {
			return false;
		}
		return true;
	}
}
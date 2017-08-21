package br.com.zonaazul.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class Mensagem {
	public void infoMensagem(String mensagem, String detalhe) {
		// Usado para quando uma acao eh executada conforme esperado.
		FacesMessage facesMessage = criarMensagem(FacesMessage.SEVERITY_INFO, mensagem, detalhe);
		adicionarMensagemContexto(facesMessage);
	}

	public void alertaMensagem(String mensagem, String detalhe) {
		// Usado para indicar que algo nao esta conforme o esperado.
		FacesMessage facesMessage = criarMensagem(FacesMessage.SEVERITY_WARN, mensagem, detalhe);
		adicionarMensagemContexto(facesMessage);
	}

	public void erroMensagem(String mensagem, String detalhe) {
		// Usado para indicar quando um processo de negocio nao foi executado por algum erro diverso.
		FacesMessage facesMessage = criarMensagem(FacesMessage.SEVERITY_ERROR, mensagem, detalhe);
		adicionarMensagemContexto(facesMessage);
	}

	private FacesMessage criarMensagem(Severity severidade, String mensagemErro, String detalhe) {
		return new FacesMessage(severidade, mensagemErro, detalhe);
	}

	private void adicionarMensagemContexto(FacesMessage facesMessage) {
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
}
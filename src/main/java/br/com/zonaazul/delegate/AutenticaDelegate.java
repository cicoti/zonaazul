package br.com.zonaazul.delegate;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.zonaazul.dto.Usuario;
import br.com.zonaazul.service.AutenticaService;
import br.com.zonaazul.util.ServiceException;


public class AutenticaDelegate implements Serializable {

	private static final long serialVersionUID = 3514779637850426447L;
	
	@Inject private AutenticaService autenticaService;

	public Usuario pesquisarUsuario(Usuario usuario) throws ServiceException {
		return autenticaService.pesquisarUsuario(usuario);
	}
	
}

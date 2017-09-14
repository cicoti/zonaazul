package br.com.zonaazul.delegate;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.zonaazul.dto.Placa;
import br.com.zonaazul.service.PlacaService;
import br.com.zonaazul.util.ServiceException;


public class PlacaDelegate implements Serializable {

	private static final long serialVersionUID = -8405149515719318391L;
	
	@Inject private PlacaService placaService;
	
	public Placa pesquisarPlacaUsuario(Placa placa) throws ServiceException{
		return placaService.pesquisarPlacaUsuario(placa);
	}

		
}

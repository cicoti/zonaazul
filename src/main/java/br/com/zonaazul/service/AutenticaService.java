package br.com.zonaazul.service;


import java.io.Serializable;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import br.com.zonaazul.dto.RespostaErro;
import br.com.zonaazul.dto.Usuario;
import br.com.zonaazul.util.BusinessServiceException;
import br.com.zonaazul.util.RuntimeServiceException;
import br.com.zonaazul.util.ServiceException;
import br.com.zonaazul.util.WebUtils;


public class AutenticaService implements Serializable  {

	private static final long serialVersionUID = -6878072357622466514L;

	public Usuario pesquisarUsuario(Usuario usuario) throws ServiceException  {

		Client client = Client.create();
		ClientResponse response = null;
		JSONObject jsonObj = new JSONObject(usuario);
		
			WebResource webResource = client.resource(WebUtils.getURLRest("v1/autentica"));
			response = webResource.type("application/json")
			   .post(ClientResponse.class, jsonObj.toString());
			
			if (response.getStatus()!=200) {
				if(response.getStatus()==400){
					String jsonString =  response.getEntity(String.class);
					RespostaErro respostaErro = (RespostaErro) new Gson().fromJson(jsonString, RespostaErro.class);
					throw new BusinessServiceException(respostaErro.getCodigo() + ": \n" + respostaErro.getErro());
					
				}
				String jsonString =  response.getEntity(String.class);
				RespostaErro respostaErro = (RespostaErro) new Gson().fromJson(jsonString, RespostaErro.class);
				throw new RuntimeServiceException(respostaErro.getCodigo() + ": \n" + respostaErro.getErro());
			}
						
			String jsonString =  response.getEntity(String.class);
			return  (Usuario)(new Gson().fromJson(jsonString, Usuario.class));

	}
	
}

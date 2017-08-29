package br.com.zonaazul.service;


import java.io.Serializable;
import java.util.List;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import br.com.zonaazul.dto.Placa;
import br.com.zonaazul.dto.RespostaErro;
import br.com.zonaazul.dto.Vaga;
import br.com.zonaazul.dto.Venda;
import br.com.zonaazul.util.BusinessServiceException;
import br.com.zonaazul.util.RuntimeServiceException;
import br.com.zonaazul.util.ServiceException;
import br.com.zonaazul.util.WebUtils;


public class PlacaService implements Serializable  {

	private static final long serialVersionUID = -4216115300088712337L;

	public Placa pesquisarPlacaUsuario(Placa placa) throws ServiceException  {

		Client client = Client.create();
		ClientResponse response = null;
		JSONObject jsonObj = new JSONObject(placa);
		
			WebResource webResource = client.resource(WebUtils.getURLRest("v1/placa/usuario"));
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
			
			placa = (new Gson().fromJson(jsonString, Placa.class));
			
			System.out.println("Placa: " + placa.getNrPlaca());

			return  placa;

	}
		
}

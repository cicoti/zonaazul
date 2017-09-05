package br.com.zonaazul.service;


import java.io.Serializable;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import br.com.zonaazul.dto.Credito;
import br.com.zonaazul.dto.RespostaErro;
import br.com.zonaazul.util.BusinessServiceException;
import br.com.zonaazul.util.RuntimeServiceException;
import br.com.zonaazul.util.ServiceException;
import br.com.zonaazul.util.WebUtils;


public class CreditoService implements Serializable  {

	private static final long serialVersionUID = -3273997282483387398L;

	public Credito buscarCredito() throws ServiceException  {

		Client client = Client.create();
		ClientResponse response = null;
		JSONObject jsonObj = new JSONObject();
		
			WebResource webResource = client.resource(WebUtils.getURLRest("v1/buscarCredito"));
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
			return  (Credito)(new Gson().fromJson(jsonString, Credito.class));

	}
	
}

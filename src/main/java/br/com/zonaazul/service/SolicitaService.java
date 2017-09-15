package br.com.zonaazul.service;


import java.io.Serializable;
import java.util.List;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import br.com.zonaazul.dto.RespostaErro;
import br.com.zonaazul.dto.Solicita;
import br.com.zonaazul.util.BusinessServiceException;
import br.com.zonaazul.util.RuntimeServiceException;
import br.com.zonaazul.util.ServiceException;
import br.com.zonaazul.util.WebUtils;


public class SolicitaService implements Serializable  {

	private static final long serialVersionUID = -6878072357622466514L;

	public void efetivar(Solicita solicita) throws ServiceException  {

		Client client = Client.create();
		ClientResponse response = null;
		JSONObject jsonObj = new JSONObject(solicita);
		
			WebResource webResource = client.resource(WebUtils.getURLRest("v1/solicita/efetivar"));
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
						
	}
	
	public List<Solicita> listarSolicitacoes() throws ServiceException  {

		Client client = Client.create();
		ClientResponse response = null;
		JSONObject jsonObj = new JSONObject();
		
			WebResource webResource = client.resource(WebUtils.getURLRest("v1/solicita"));
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
			
			List<Solicita> listaSolicitacoes = (new Gson().fromJson(jsonString, new TypeToken<List<Solicita>>(){}.getType()));

			return  listaSolicitacoes;
						
	}
	
}

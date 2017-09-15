package br.com.zonaazul.delegate;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.zonaazul.dto.Placa;
import br.com.zonaazul.dto.Solicita;
import br.com.zonaazul.service.SolicitaService;
import br.com.zonaazul.util.BusinessServiceException;
import br.com.zonaazul.util.ServiceException;


public class SolicitaDelegate implements Serializable {

	private static final long serialVersionUID = 374187132122344663L;
	
	@Inject private SolicitaService solicitaService;

	public void efetivar(Solicita solicita) throws ServiceException {
		solicitaService.efetivar(solicita);
	}
	
	public void pesquisarSolicitacaoPorPlaca(Placa placa) throws ServiceException{
		List<Solicita> listaSolicitacoes = solicitaService.listarSolicitacoes();
		for(Solicita solicita:listaSolicitacoes){
			if(solicita.getPlaca().getNrPlaca().equals(placa.getNrPlaca())){
				throw new BusinessServiceException("A placa informada se encontra em vaga.");
			}
		}
	}
	
	
}

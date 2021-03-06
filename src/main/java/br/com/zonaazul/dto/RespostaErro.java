package br.com.zonaazul.dto;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties ( ignoreUnknown = true )
public class RespostaErro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String evento;
	private String codigo;
	private String erro;
	private String descricao;
	
}

package br.com.zonaazul.dto;

import java.io.Serializable;
import java.util.Date;

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
public class Placa implements Serializable { 

	private static final long serialVersionUID = 3448481536646064647L;
	
	private Long id;
	private Long idUsuario;
	private String nrPlaca;
	private Date dtCadastro;
		
}

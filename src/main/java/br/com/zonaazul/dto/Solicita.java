package br.com.zonaazul.dto;

import java.io.Serializable;
import java.sql.Date;

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
public class Solicita implements Serializable { 

	private static final long serialVersionUID = 5604678881122623511L;
	
	private Long id;
	private Long idUsuario;
	private Long idVenda;
	private Long idVaga;
	private Long idPlaca;
	private Date inicioPeriodo;
	private Date fimPeriodo;
	private boolean extensao;
	private boolean negado;
	private String motivo;
	
}

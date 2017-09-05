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
public class Periodo implements Serializable { 

	private static final long serialVersionUID = -5705335846467455182L;
	
	private String inicio;
	private String fim;
		
}

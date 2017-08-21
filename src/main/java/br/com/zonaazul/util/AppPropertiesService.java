package br.com.zonaazul.util;

import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

import javax.annotation.PostConstruct;

public class AppPropertiesService implements Serializable {
	
	private static final long serialVersionUID = -863959058996753445L;
	private Properties appProperties;
	
	/**
	 * Retorna propriedade String
	 * @param key
	 */
	public String getPropertyString(String key) {
		return appProperties.containsKey(key) ? (String)appProperties.get(key) : null;
	}

	/**
	 * Retorna propriedade Integer
	 * @param key
	 */
	public Integer getPropertyInteger(String key) {
		return appProperties.containsKey(key) ? Integer.parseInt((String)appProperties.get(key)) : null;
	}

	/**
	 * Retorna propriedade Long
	 * @param key
	 */
	public Long getPropertyLong(String key) {
		return appProperties.containsKey(key) ? Long.parseLong((String)appProperties.get(key)) : null;
	}
	
	/**
	 * Le arquivo de properties da aplicacao. Futuramente poderemos sobrescrever
	 * várias propriedades lendo app.properties, app_1.properties,
	 * app_2.properties, etc
	 * 
	 * @author Rubens
	 */
	@PostConstruct
	public void initialize() {
		appProperties = new Properties();

		try {
			appProperties.load(this.getClass().getClassLoader()
					.getResourceAsStream("application.properties"));
		} catch (IOException e) {
			e.printStackTrace();
			appProperties = null;
		}
	}
}

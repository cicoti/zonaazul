package br.com.zonaazul.util;

public abstract class ServiceException extends Exception {

	/** TODO javadoc */
	private static final long serialVersionUID = 2431582353016948731L;

	/**
	 * TODO javadoc
	 *
	 * @param message
	 * @param throwable
	 */
	public ServiceException(String message, Throwable throwable) {
		super(message, throwable);
	}

	/**
	 * TODO javadoc
	 *
	 * @param message
	 */
	public ServiceException(String message) {
		super(message);
	}

}
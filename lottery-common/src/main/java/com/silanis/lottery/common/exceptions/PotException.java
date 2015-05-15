package com.silanis.lottery.common.exceptions;

/**
 * @author am
 * Exception that represents an error related with the pot
 */
public class PotException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PotException(String message) {
		super(message);
	}
}

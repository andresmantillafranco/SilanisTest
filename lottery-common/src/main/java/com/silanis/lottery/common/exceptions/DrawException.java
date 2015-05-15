package com.silanis.lottery.common.exceptions;

/**
 * @author am
 * Exception that represents an error related with a draw
 */
public class DrawException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DrawException(String message) {
		super(message);
	}
}

package com.sutils.exception;

/**
 * 
 * @author Senthilnathan
 * 
 * This is a custome exception to handle any validation exceptions.
 *
 */

public class InvalidInputException extends RuntimeException{
	
	
	public InvalidInputException(String message) {
		
		super(message);
	}


}

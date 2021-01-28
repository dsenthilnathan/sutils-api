package com.sutils.exception;

/**
 * 
 * @author Senthilnathan
 * 
 * This is the Exception handler for the Sutils-api to handle all the major exceptions/error.
 * 
 */

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler{
	
	
	// To handle any missing parameter in the Request
	
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(
	  MissingServletRequestParameterException ex, HttpHeaders headers, 
	  HttpStatus status, WebRequest request) {
		
	    String error = ex.getParameterName() + " parameter is missing";
	    
	    return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST,error,ex));
	}
	
	

	 // To handle any missing path variable 
	 @Override
	
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		 String error = "Missing Required Param : n (integer)";
			
			return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST,error,ex));
		 
	}
	 
	 
	 

	// To handle any custom validation exceptions
	 
	@org.springframework.web.bind.annotation.ExceptionHandler(InvalidInputException.class)
	  @ResponseStatus(HttpStatus.BAD_REQUEST)
	  ResponseEntity<Object> handleInvalidInputException(InvalidInputException e) {
	    
		 String error = "Invalid Input";
	
			return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST,error,e));
	  }

	
	// To handle the constraints violations
	
	 @org.springframework.web.bind.annotation.ExceptionHandler(ConstraintViolationException.class)
	  @ResponseStatus(HttpStatus.BAD_REQUEST)
	  ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e) {
	    
		 String error = "Invalid Input";
			
			return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST,error,e));
	  }
	 
	 
	 // To handle any input type mismatches
	 @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentTypeMismatchException.class )
	  public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
			  MethodArgumentTypeMismatchException ex, WebRequest request) {
		 
		 String error = "Invalid Input";
			
			return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST,error,"java.lang.NumberFormatException - Only whole numbers are allowed as input"));
		 
	 }
	
	 
	
	

	
	 private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
	       return new ResponseEntity<>(apiError, apiError.getStatus());
	   }
	
}

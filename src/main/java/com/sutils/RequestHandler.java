package com.sutils;


/**
 * 
 * @author Senthilnathan
 * 
 * This is the RestController for the SUtils-Api
 * 
 */

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sutils.service.SUtilService;
import com.sutils.vo.Primes;



@RestController
@Validated
public class RequestHandler {
	
	@Autowired
	
	SUtilService sutil;
	
	
	private final Logger logger = LoggerFactory.getLogger(RequestHandler.class);
	
	
	
	@RequestMapping( value = "/primes/{n}", method=RequestMethod.GET , produces= {"application/json", "application/xml"})
	
	Primes getPrimes(@PathVariable(required = true) @Min(1) @Max(100)  int n) {
		 
		logger.info("getPrimes - Entry");
		
		return sutil.getPrimes(n);
		
		
	}
	
	
	
	@RequestMapping( value = "/welcome", method=RequestMethod.GET , produces= {"application/json", "application/xml"})
	
	String getWelcomeMessage () {
		 
		logger.info("getWelcome Message - Entry");
		
		return sutil.getWelcomeMessage();
		
		
	}

}

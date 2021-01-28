package com.sutils.service;

/**
 * 
 * @author Senthilnathan
 * 
 * This is the Service implementation for the SUtilService
 * 
 */

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.sutils.exception.InvalidInputException;
import com.sutils.vo.Primes;

@Service

public class SUtilService implements SUtilServiceInterface{
	
	private final Logger logger = LoggerFactory.getLogger(SUtilService.class);
	
	@Autowired
	
	private Environment env;

	@Override
	public String getWelcomeMessage() {
		
		logger.info("getWelcomeMessage - Entry");
		
		return "Welcome to SUtil Api - A group of Utility API for common functionalities";
	}

	
	
	@Override
	@Cacheable(value="primesCache",key="#n")
	public Primes getPrimes(int n) {
		
		logger.info("getPrimes - Entry");
		
		int lowerBoundary = Integer.parseInt(env.getProperty("primes.min.input"));
		
		int upperBoundary = Integer.parseInt(env.getProperty("primes.max.input"));
		
		List<Integer> result = new ArrayList();
		
		if(n==2) {
			result.add(n);
			
			return constructPrimes(n, result);
		}else if(n<lowerBoundary || n>upperBoundary) {
			throw new InvalidInputException("Invalid Input : Input is not within the range of "+lowerBoundary+" - "+upperBoundary);
		}
		
		IntStream.range(2, n)
		.filter(i->isPrime(i) == true)
		.forEach(i->result.add(i));
		
		return constructPrimes(n, result);
		
		
	}
	
	
	
	
	private boolean isPrime(int number) 
	{
	    if(number <= 2)
	        return number == 2;
	    else
	        return  (number % 2) != 0
	                &&
	                IntStream.rangeClosed(3, (int) Math.sqrt(number))
	                .filter(n -> n % 2 != 0)
	                .noneMatch(n -> (number % n == 0));
	}
	
	private Primes constructPrimes(int initial, List<Integer> result) {
		

		return new Primes(initial,result);
		
	}
	
	
	public static void main(String[] args) {
		
		SUtilService s = new SUtilService();
		System.out.println(s.getPrimes(30));
	}
	
	
	

}

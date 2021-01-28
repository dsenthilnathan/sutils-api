package com.sutils.service;

/**
 * 
 * @author Senthilnathan
 * 
 * This is the Service Interface to list out the common service available in the SUtils-Api
 * 
 */

import com.sutils.vo.Primes;

public interface SUtilServiceInterface {
	
	String getWelcomeMessage();
	
	Primes getPrimes(int n);

}

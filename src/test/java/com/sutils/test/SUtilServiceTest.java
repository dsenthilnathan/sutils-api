package com.sutils.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sutils.exception.InvalidInputException;
import com.sutils.service.SUtilService;
import com.sutils.vo.Primes;

@SpringBootTest
public class SUtilServiceTest {
	
	
	@Autowired
	SUtilService service;

	

    @DisplayName("Test - Welcome Message")
    @Test
    void testGet() {
        assertEquals("Welcome to SUtil Api - A group of Utility API for common functionalities", service.getWelcomeMessage());
    }
	
    @DisplayName("Test - getPrime - 1")
    @Test
    void testPrime_case1() {
    	
    	int input = 30;
    	
    	int expectedOpListSize = 10;
       
    	Primes primes = service.getPrimes(input);
    	
    	
    	assertEquals(input, primes.getInitial());
    	
    	
    	assertEquals(expectedOpListSize, primes.getPrimes().size());

    	
    	primes.getPrimes().forEach(i-> assertEquals(true,isPrime(i)));
    	
    	
    	
    }
    
    
    @DisplayName("Test - getPrime - 2")
    @Test
    void testPrime_case2() {
    	
    	
    	 Assertions.assertThrows(InvalidInputException.class, () -> {
    		 	int input = -1;
    		 	service.getPrimes(input);
    		  });
    	
    	
        Assertions.assertThrows(InvalidInputException.class, () -> {
		 	int input = 000;
		 	service.getPrimes(input);
		  });
        
    	
       	
        Assertions.assertThrows(InvalidInputException.class, () -> {
		 	int input = 001;
		 	service.getPrimes(input);
		  });
        
        
        
        Assertions.assertThrows(InvalidInputException.class, () -> {
		 	int input = 200;
		 	service.getPrimes(input);
		  });
    	
    	
    	
    }
    
    
    @DisplayName("Test - getPrime - 3")
    @Test
    void testPrime_case3() {
    	
    	int input = 2;
    	
    
    	int expectedOpListSize = 1;
        
    	Primes primes = service.getPrimes(input);
    	
    	
    	assertEquals(input, primes.getInitial());
    	
    	
    	assertEquals(expectedOpListSize, primes.getPrimes().size());

    	
    	assertEquals(input,primes.getPrimes().get(0).intValue());
    	
    	
    }
    
    
    boolean isPrime(int num) {
    	
    	
    	 boolean flag = false;
    	    for (int i = 2; i <= num / 2; ++i) {
    	      // condition for nonprime number
    	      if (num % i == 0) {
    	        flag = true;
    	        break;
    	      }
    	    }
    	
    	 return !flag;
    }
	
    
    
    

}

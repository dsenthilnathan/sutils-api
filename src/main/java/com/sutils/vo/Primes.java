package com.sutils.vo;

import java.io.Serializable;
import java.util.List;

public class Primes implements Serializable{
	

	private static final long serialVersionUID = 1L;

	int Initial;
	
	List<Integer> primes;
	
	
	public Primes(int initial, List<Integer> primes) {
		
		this.Initial = initial;
		this.primes = primes;
		
	}

	public int getInitial() {
		return Initial;
	}

	public void setInitial(int initial) {
		Initial = initial;
	}

	public List<Integer> getPrimes() {
		return primes;
	}

	public void setPrimes(List<Integer> primes) {
		primes = primes;
	}
	
	

}

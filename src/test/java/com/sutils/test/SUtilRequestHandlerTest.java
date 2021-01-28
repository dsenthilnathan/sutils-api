package com.sutils.test;


import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;




@RunWith(SpringRunner.class)

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

@AutoConfigureMockMvc

public class SUtilRequestHandlerTest {
	
	 // bind the above RANDOM_PORT
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_welcome() throws Exception {

        MvcResult result = mockMvc.perform(get("http://localhost:" + port + "/welcome"))
         				.andDo(print())
         				.andExpect(status().isOk())
         				.andReturn();
        
        assertEquals("Welcome to SUtil Api - A group of Utility API for common functionalities", result.getResponse().getContentAsString());
         				

    }
    
    @Test
    
    public void test_invalid_input_error_response_case1() throws Exception {
    	
    mockMvc.perform(get("http://localhost:" + port + "/primes/1"))
  				.andDo(print())
  				.andExpect(status().isBadRequest())
  				.andExpect(jsonPath("$.status", is("BAD_REQUEST")))
  				.andExpect(jsonPath("$.message", is("Invalid Input")))
  				.andExpect(jsonPath("$.debugMessage",org.hamcrest.Matchers.startsWith("Invalid Input : Input is not within the range")));
  						
  			
    }
 
    
   @Test
    
    public void test_invalid_input_error_response_case2() throws Exception {
    	
    mockMvc.perform(get("http://localhost:" + port + "/primes/300"))
  				.andDo(print())
  				.andExpect(status().isBadRequest())
  				.andExpect(jsonPath("$.status", is("BAD_REQUEST")))
  				.andExpect(jsonPath("$.message", is("Invalid Input")))
  				.andExpect(jsonPath("$.debugMessage",org.hamcrest.Matchers.startsWith("Invalid Input : Input is not within the range")));
		
    	
    }

   
   @Test
   
   public void test_invalid_input_error_response_case3() throws Exception {
   	
   mockMvc.perform(get("http://localhost:" + port + "/primes/2.5"))
 				.andDo(print())
 				.andExpect(status().isBadRequest())
 				.andExpect(jsonPath("$.status", is("BAD_REQUEST")))
 				.andExpect(jsonPath("$.message", is("Invalid Input")))
 				.andExpect(jsonPath("$.debugMessage",org.hamcrest.Matchers.startsWith("java.lang.NumberFormatException - Only whole numbers are allowed as input")));
		
   	
   }
   
   
   @Test
   
   public void test_positive_response_case1() throws Exception {
   	
	   mockMvc.perform(get("http://localhost:" + port + "/primes/30"))
 				.andDo(print())
 				.andExpect(status().isOk())
 				.andExpect(jsonPath("$.initial", org.hamcrest.Matchers.equalTo(30)))
 				.andExpect(jsonPath("$.primes").isArray())
 				.andExpect(jsonPath("$.primes").value(containsInAnyOrder(2,3,5,7,11,13,17,19,23,29)));
                                                                               
   	
   }

}

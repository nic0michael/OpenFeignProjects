package za.nico.customer.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import za.nico.customer.dtos.CustomerDto;



@WebMvcTest(CustomerRestController.class)
public class CustomerRestControllerTest {
	
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
	private CustomerRestController customerRestController;
    
    
    @Test
    public void getTest() {
    	Long id = 7007L;
		ResponseEntity<CustomerDto> customer = customerRestController.getUserById(id );
		
    }

}

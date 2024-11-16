package za.nico.customer.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import za.nico.customer.dtos.CustomerDto;


@SpringBootTest
public class CustomerRestControllerTest {
	
	
    
    @Autowired
	private CustomerRestController customerRestController;
    

    @Disabled
    @Test
    public void getUserByIdTest() {
    	Long id = 7007L;
		ResponseEntity<CustomerDto> response = customerRestController.getUserById(id );
		assertNotNull(response);
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isNotNull();
        CustomerDto customer = response.getBody();
		Long theId = customer.getId();
		 assertThat(theId).isEqualTo(id);		
    }

    @Disabled
    @Test
    public void createCustomerTest(){
    	CustomerDto customer = makeCustomerDto();
		ResponseEntity<CustomerDto> response = customerRestController.createCustomer(customer );
		assertNotNull(response);
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isNotNull();
        CustomerDto theCustomer = response.getBody();
        Long id = customer.getId();
        Long theId = theCustomer.getId();
        assertThat(theId).isEqualTo(id);	
		
    }

	private CustomerDto makeCustomerDto() {
		CustomerDto customer = new CustomerDto();
		customer.setEmail("rack@ripper.org");
		customer.setId(9999L);
		customer.setName("Jack the Ripper");
		return customer;
	}

}

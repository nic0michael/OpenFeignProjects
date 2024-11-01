package za.nico.customer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import za.nico.customer.dtos.CustomerDto;
import za.nico.customer.services.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerRestController {
	
	private CustomerService service;

	public CustomerRestController(CustomerService service) {
		this.service = service;
	}
	

    @GetMapping("/")
    public ResponseEntity<CustomerDto> getUser( ) {
        Long id = 7007L;
    	CustomerDto customer = service.findCustomerById(id);
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getUserById(@PathVariable("id") Long id) {
        CustomerDto customer = service.findCustomerById(id);
        return ResponseEntity.ok(customer);
    }

    @PostMapping("/")
    public ResponseEntity<CustomerDto> createUser(@RequestBody CustomerDto customer) {
    	CustomerDto customerDto = service.createCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerDto);
    }


}

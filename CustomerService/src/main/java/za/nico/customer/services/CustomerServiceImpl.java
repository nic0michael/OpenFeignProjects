package za.nico.customer.services;

import org.springframework.stereotype.Service;

import za.nico.customer.dtos.CustomerDto;
import za.nico.customer.feignclients.CustomerClient;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	private final CustomerClient customerClient;
	
	
	public CustomerServiceImpl(CustomerClient customerClient) {
		super();
		this.customerClient = customerClient;
	}

	@Override
	public CustomerDto findCustomerById(Long id) {
		CustomerDto customer = customerClient.findCustomerById(id);
		return customer;
	}

	@Override
	public CustomerDto createCustomer(CustomerDto customer) {
		return customerClient.createCustomer(customer);
	}

}

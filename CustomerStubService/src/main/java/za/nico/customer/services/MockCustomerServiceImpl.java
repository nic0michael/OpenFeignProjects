package za.nico.customer.services;

import org.springframework.stereotype.Service;

import za.nico.customer.dtos.CustomerDto;

@Service
public class MockCustomerServiceImpl implements CustomerService{

	private String name = "James Bond";
	private String email = "jamesB@mi6.gov.uk";
	private CustomerDto customer;
	

	@Override
	public CustomerDto findCustomerById(Long id) {
		makeCustomerDto(id);
		return customer;
	}

	@Override
	public CustomerDto createCustomer(CustomerDto customerDto) {
		customer = customerDto;
		return customer;
	}

	private void  makeCustomerDto(Long theId) {
		if(null == customer) {			
			customer = new CustomerDto();
			customer.setEmail(email);
			customer.setName(name);
		}
		customer.setId(theId);
	}
	


}

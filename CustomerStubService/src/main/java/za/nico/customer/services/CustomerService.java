package za.nico.customer.services;

import za.nico.customer.dtos.CustomerDto;

public interface CustomerService {

	CustomerDto findCustomerById(Long id);

	CustomerDto createCustomer(CustomerDto customer);

}

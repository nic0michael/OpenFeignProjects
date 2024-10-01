## OpenFeign Spring Boot Example Project

---
## CustomerService - Spring Boot Micro-service with OpenFeign Client

This project demonstrates a Spring Boot application (`CustomerServiceApplication`) using OpenFeign to communicate with another Spring Boot service (a `user-service`). The application contains REST controllers, DTOs, service classes, and Feign clients to handle customer data through HTTP requests.

## Table of Contents
1. [Prerequisites](#prerequisites)
2. [Project Structure](#project-structure)
3. [Description](#description)
4. [Installation and Running](#installation-and-running)
5. [Example Usage](#example-usage)
6. [Code Overview](#code-overview)

## Prerequisites

- Java 8 or higher
- Maven
- Spring Boot 2.5+
- OpenFeign dependency for creating REST clients

## Project Structure

```
za/nico/customer/
├── controllers/
│   └── CustomerRestController.java
├── dtos/
│   └── CustomerDto.java
├── feignclients/
│   └── CustomerClient.java
├── services/
│   ├── CustomerService.java
│   └── CustomerServiceImpl.java
└── CustomerServiceApplication.java
```


## Description

This Spring Boot project is a customer service that communicates with another microservice (`user-service`) using OpenFeign. It demonstrates how to define Feign clients and integrate them with a Spring service layer to handle business logic and API requests efficiently.

## Installation and Running

1. Clone the repository.
2. Navigate to the project root.
3. Run the following command to build and start the application:

   ```bash
   mvn spring-boot:run
   ```

4. The application will be running at `http://localhost:8080`.

## Example Usage

You can interact with the application via HTTP requests. Here are some examples:

- **Get Customer by ID**:
  ```bash
  GET http://localhost:8080/customers/{id}
  ```

- **Create Customer**:
  ```bash
  POST http://localhost:8080/customers/
  Content-Type: application/json

  {
      "name": "John Doe",
      "email": "john.doe@example.com"
  }
  ```

## Code Overview

### 1. `CustomerServiceApplication.java`

```java
package za.nico.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}
}
```

This is the main entry point for the Spring Boot application. It enables Feign clients through `@EnableFeignClients`.

### 2. `CustomerRestController.java`

```java
package za.nico.customer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import za.nico.customer.dtos.CustomerDto;
import za.nico.customer.services.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerRestController {

	@Autowired
	private CustomerService service;

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
```

This controller exposes two endpoints:
- GET `/customers/{id}`: Retrieves a customer by their ID.
- POST `/customers/`: Creates a new customer.

### 3. `CustomerDto.java`

```java
package za.nico.customer.dtos;

public class CustomerDto {
	private String name;
	private Long id;
	private String email;

	public CustomerDto() {}

	public CustomerDto(String name, Long id, String email) {
		this.name = name;
		this.id = id;
		this.email = email;
	}

	// Getters and setters
}
```

This DTO (Data Transfer Object) is used for transferring customer data between the layers of the application.

### 4. `CustomerClient.java`

```java
package za.nico.customer.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import za.nico.customer.dtos.CustomerDto;

@FeignClient(name = "user-service", url = "http://localhost:8081/customers")
public interface CustomerClient {

    @GetMapping("/{id}")
    CustomerDto findCustomerById(@PathVariable("id") Long id);

    @PostMapping("/")
    CustomerDto createCustomer(@RequestBody CustomerDto customer);
}
```

This is the Feign client that allows the `CustomerService` to communicate with the `user-service` running on `http://localhost:8081`. It defines methods to get a customer by ID and create a customer.

### 5. `CustomerService.java`

```java
package za.nico.customer.services;

import za.nico.customer.dtos.CustomerDto;

public interface CustomerService {

	CustomerDto findCustomerById(Long id);

	CustomerDto createCustomer(CustomerDto customer);
}
```

This service interface defines the core customer operations.

### 6. `CustomerServiceImpl.java`

```java
package za.nico.customer.services;

import org.springframework.stereotype.Service;
import za.nico.customer.dtos.CustomerDto;
import za.nico.customer.feignclients.CustomerClient;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	private final CustomerClient customerClient;

	public CustomerServiceImpl(CustomerClient customerClient) {
		this.customerClient = customerClient;
	}

	@Override
	public CustomerDto findCustomerById(Long id) {
		return customerClient.findCustomerById(id);
	}

	@Override
	public CustomerDto createCustomer(CustomerDto customer) {
		return customerClient.createCustomer(customer);
	}
}
```

The `CustomerServiceImpl` class implements the `CustomerService` interface and uses the Feign client (`CustomerClient`) to handle communication with the `user-service`.

## CustomerStubService - Spring Boot Micro-Service 
This is a Mock Spring Boot Micro-Service that returns mocked values so that the above Spring Boot Project can test its ability to use a Feign Client to communicate with this Micro-service. 

## Conclusion

This project is an example of how to use Spring Boot and OpenFeign to create a service that communicates with another microservice. It demonstrates basic operations like fetching and creating customers while utilizing OpenFeign to simplify REST communication.
```



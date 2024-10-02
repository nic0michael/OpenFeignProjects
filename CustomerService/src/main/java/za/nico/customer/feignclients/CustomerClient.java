package za.nico.customer.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import za.nico.customer.dtos.CustomerDto;

/**
 *  You can use hardcoded values:
 *  @FeignClient(name = "user-service", url = "http://localhost:8081/customers")
 *  
 *  Or values from properties file :
 *  @FeignClient(name = "${customer.client.service}", url = "${customer.client.url}")

 */
@FeignClient(name = "${customer.client.service}", url = "${customer.client.url}")
public interface CustomerClient {

    @GetMapping("/{id}")
    CustomerDto findCustomerById(@PathVariable("id") Long id);

    @PostMapping("/")
    CustomerDto createCustomer(@RequestBody CustomerDto customer);

}

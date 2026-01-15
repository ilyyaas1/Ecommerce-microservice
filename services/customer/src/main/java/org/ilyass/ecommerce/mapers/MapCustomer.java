package org.ilyass.ecommerce.mapers;

import org.ilyass.ecommerce.dtos.CustomerRequest;
import org.ilyass.ecommerce.dtos.CustomerResponse;
import org.ilyass.ecommerce.entities.Customer;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MapCustomer {

    public Customer customerRequestToCustomer (CustomerRequest request) {
        Customer customer = new Customer();
        customer.setId(UUID.randomUUID().toString());
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());
        customer.setAddress(request.getAddress());
        return customer;
    }

    public CustomerResponse customerToCustomerResponse (Customer customer) {
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setId(customer.getId());
        customerResponse.setFirstName(customer.getFirstName());
        customerResponse.setLastName(customer.getLastName());
        customerResponse.setEmail(customer.getEmail());

        return customerResponse;
    }

}

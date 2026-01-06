package org.ilyass.ecommerce.repositories;

import org.ilyass.ecommerce.entities.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
    public Customer findCustomerByEmail(String email);
}

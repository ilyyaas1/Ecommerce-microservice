package org.ilyass.ecommerce.services;

import org.ilyass.ecommerce.dtos.CustomerRequest;
import org.ilyass.ecommerce.dtos.CustomerResponse;
import org.ilyass.ecommerce.entities.Customer;
import org.ilyass.ecommerce.exceptions.CustomerNotFoundException;
import org.ilyass.ecommerce.mapers.MapCustomer;
import org.ilyass.ecommerce.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private CustomerRepository repository;
    private MapCustomer mapCustomer;
    public CustomerService(CustomerRepository repository, MapCustomer mapCustomer) {
        this.repository = repository;
        this.mapCustomer = mapCustomer;
    }



    public List<CustomerResponse> findAll() {
        return repository.findAll()
                .stream().map(mapCustomer::customerToCustomerResponse).collect(Collectors.toList());
    }
    public CustomerResponse createCustomer(CustomerRequest request){
        Customer customer = mapCustomer.customerRequestToCustomer(request);
        Customer savedCustomer =  repository.save(customer);
        return mapCustomer.customerToCustomerResponse(savedCustomer);
    }
    public CustomerResponse updateCustomer(String id, CustomerRequest request){
        Customer customer = repository.findById(id).orElseThrow(()-> new CustomerNotFoundException(
            String.format("cannot update customer : No Customer found with the ID :: %s",request.getId())
        ));

        if(request.getFirstName() != null){

            customer.setFirstName(request.getFirstName());
        }
        if(request.getLastName() != null){
            customer.setLastName(request.getLastName());
        }
        if(request.getAddress() != null){
            customer.setAddress(request.getAddress());
        }
        if(request.getEmail() != null){
            customer.setEmail(request.getEmail());
        }
        if(request.getPhone() != null){
            customer.setPhone(request.getPhone());
        }
        Customer savedCustomer = repository.save(customer);
        return mapCustomer.customerToCustomerResponse(savedCustomer);
    }

    public CustomerResponse findCustomerById(String id) {
        Customer customer  =  repository.findById(id).orElseThrow(()-> new CustomerNotFoundException(
                String.format("cannot find  customer : with the ID :: %s",id))
        );
        return mapCustomer.customerToCustomerResponse(customer);
    }
    public CustomerResponse findCustomerByEmail(String email) {
        Customer customer =  repository.findCustomerByEmail(email);
        return mapCustomer.customerToCustomerResponse(customer);
    }

    public void deleteCustomer(String id) {
        Customer customer = repository.findById(id).orElse(null);
        repository.delete(customer);
    }

    public Boolean exitsCustomer(String id) {
        return repository.findById(id).isPresent();
    }



}

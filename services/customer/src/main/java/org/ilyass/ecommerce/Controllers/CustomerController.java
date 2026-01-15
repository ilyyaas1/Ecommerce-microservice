package org.ilyass.ecommerce.Controllers;

import jakarta.validation.Valid;
import org.ilyass.ecommerce.dtos.CustomerRequest;
import org.ilyass.ecommerce.dtos.CustomerResponse;
import org.ilyass.ecommerce.mapers.MapCustomer;
import org.ilyass.ecommerce.services.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private CustomerService service;
    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody @Valid CustomerRequest request) {
        return  ResponseEntity.ok(service.createCustomer(request));
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable String customerId, @RequestBody @Valid CustomerRequest request) {
        return  ResponseEntity.ok(service.updateCustomer(customerId, request));
    }
    @DeleteMapping("/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable String customerId) {
        service.deleteCustomer(customerId);
        return ResponseEntity.ok("Customer deleted successfully");
    }

    @GetMapping("/exits/{customerId}")
    public ResponseEntity<Boolean> exitsCustomerById(@PathVariable String customerId) {
        return ResponseEntity.ok(service.exitsCustomer(customerId));
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable String customerId) {

        return ResponseEntity.ok(service.findCustomerById(customerId));
    }

}

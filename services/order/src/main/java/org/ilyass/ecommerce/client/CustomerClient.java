package org.ilyass.ecommerce.client;


import org.ilyass.ecommerce.dtos.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
        name="customer-service",
        url="${application.config.customer-url}"
)
public interface  CustomerClient {
    @GetMapping("/{customerId}")
    Optional<CustomerResponse> findCustomerById(@PathVariable String customerId);
}

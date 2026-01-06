package org.ilyass.ecommerce.client;

import org.ilyass.ecommerce.dtos.PurchaseRequest;
import org.ilyass.ecommerce.dtos.PurchaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(
        name="product-service",
        url="${application.config.product-url}"
)
public interface ProductClient {

    @PostMapping("/purchase")
     List<PurchaseResponse> purchaseProduct(@RequestBody List<PurchaseRequest> productPurchaseRequest);


}

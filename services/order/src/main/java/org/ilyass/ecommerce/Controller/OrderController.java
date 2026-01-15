package org.ilyass.ecommerce.Controller;


import jakarta.validation.Valid;
import org.ilyass.ecommerce.dtos.OrderRequest;
import org.ilyass.ecommerce.dtos.OrderResponse;
import org.ilyass.ecommerce.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping("/order")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody @Valid OrderRequest orderRequest) {
        return ResponseEntity.ok(service.createOrder(orderRequest));
    }

    @GetMapping("/")
    public ResponseEntity<List<OrderResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> findById(@PathVariable Integer orderId) {
        return ResponseEntity.ok(service.findById(orderId));
    }






}

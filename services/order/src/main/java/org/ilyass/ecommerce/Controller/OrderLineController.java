package org.ilyass.ecommerce.Controller;

import lombok.RequiredArgsConstructor;
import org.ilyass.ecommerce.dtos.OrderLineResponse;
import org.ilyass.ecommerce.mappers.OrderLineMapper;
import org.ilyass.ecommerce.services.OrderLineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orderLines")
@RequiredArgsConstructor
public class OrderLineController {

    private final OrderLineService orderLineService;


    @GetMapping("/{orderLineId}")
    public ResponseEntity<OrderLineResponse> getOrderLine(@PathVariable Integer orderLineId) {

        return ResponseEntity.ok(orderLineService.getOrderLineById(orderLineId));
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderLineResponse>> findByOrderId(@PathVariable Integer orderId) {
        return ResponseEntity.ok(orderLineService.findAllByOrderId(orderId));
    }
}

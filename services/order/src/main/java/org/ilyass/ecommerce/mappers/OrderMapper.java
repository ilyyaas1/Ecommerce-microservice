package org.ilyass.ecommerce.mappers;

import org.ilyass.ecommerce.dtos.OrderRequest;
import org.ilyass.ecommerce.dtos.OrderResponse;
import org.ilyass.ecommerce.entities.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public Order orderRequestToOrder(OrderRequest orderRequest){
        return Order.builder()
                .id(orderRequest.getId())
                .reference(orderRequest.getReference())
                .totalAmount(orderRequest.getTotalAmount())
                .paymentMethod(orderRequest.getPaymentMethod())
                .build();
    }


    public OrderResponse orderToOrderResponse(Order order) {
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(order.getId());
        orderResponse.setCustomerId(order.getCustomerId());
        orderResponse.setPaymentMethod(order.getPaymentMethod());
        orderResponse.setReference(order.getReference());
        orderResponse.setCreatedDate(order.getCreatedAt());

        return orderResponse;
    }
}

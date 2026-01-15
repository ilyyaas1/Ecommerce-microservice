package org.ilyass.ecommerce.mappers;

import org.ilyass.ecommerce.dtos.OrderLineRequest;
import org.ilyass.ecommerce.dtos.OrderLineResponse;
import org.ilyass.ecommerce.entities.Order;
import org.ilyass.ecommerce.entities.OrderLine;
import org.springframework.stereotype.Component;

@Component
public class OrderLineMapper {

    public OrderLine  toOrderLine(OrderLineRequest orderLineRequest){
        return OrderLine.builder()
                .id(orderLineRequest.getId())
                .productId(orderLineRequest.getProductId())
                .order(Order.builder()
                        .id(orderLineRequest.getOrderId()).build())
                .build();

    }
    public OrderLineResponse toOrderLineResponse(OrderLine orderLine){
        OrderLineResponse orderLineResponse = new OrderLineResponse();
        orderLineResponse.setId(orderLine.getId());
        orderLineResponse.setProductId(orderLine.getProductId());
        orderLineResponse.setQuantity(orderLine.getQuantity());


        return orderLineResponse;

    }
}

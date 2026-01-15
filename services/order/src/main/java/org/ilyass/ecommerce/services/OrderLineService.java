package org.ilyass.ecommerce.services;

import lombok.RequiredArgsConstructor;
import org.ilyass.ecommerce.Repositories.OrderLineRepository;

import org.ilyass.ecommerce.dtos.OrderLineRequest;
import org.ilyass.ecommerce.dtos.OrderLineResponse;
import org.ilyass.ecommerce.entities.OrderLine;
import org.ilyass.ecommerce.mappers.OrderLineMapper;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderLineService {
    private final OrderLineRepository repository;
    private final OrderLineMapper mapper;

    public Integer saveOrderLine(OrderLineRequest orderLineRequest) {
            var orderLine = mapper.toOrderLine(orderLineRequest);
            return repository.save(orderLine).getId();
    }

    public OrderLineResponse getOrderLineById(Integer id) {
         return mapper.toOrderLineResponse(repository.findById(id).orElseThrow(()-> new RuntimeException("Order line not found")));

    }

    public List<OrderLineResponse> findAllByOrderId(Integer orderId) {
        return repository.findAllByOrderId(orderId).stream().map(mapper::toOrderLineResponse).collect(Collectors.toList());

    }



}

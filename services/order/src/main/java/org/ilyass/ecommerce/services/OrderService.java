package org.ilyass.ecommerce.services;

import lombok.RequiredArgsConstructor;

import org.ilyass.ecommerce.Repositories.OrderRepository;
import org.ilyass.ecommerce.client.CustomerClient;
import org.ilyass.ecommerce.client.ProductClient;
import org.ilyass.ecommerce.dtos.*;
import org.ilyass.ecommerce.kafka.OrderConfirmation;
import org.ilyass.ecommerce.kafka.OrderProducer;
import org.ilyass.ecommerce.mappers.OrderMapper;
import org.ilyass.ecommerce.payment.PaymentClient;
import org.ilyass.ecommerce.payment.PaymentRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {


    private final CustomerClient customerClient;
    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final ProductClient productClient;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;
    public OrderResponse createOrder(OrderRequest orderRequest) {
        var customer = this.customerClient.findCustomerById(orderRequest.getCustomerId()).orElseThrow(
                () -> new IllegalArgumentException("Customer not found")
        );
        var purchasedProducts = this.productClient.purchaseProduct(orderRequest.getProducts());
        var order = this.repository.save(mapper.orderRequestToOrder(orderRequest));
        for(PurchaseRequest purchaseRequest : orderRequest.getProducts()){
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            orderRequest.getId(),
                            purchaseRequest.getProductId(),
                            purchaseRequest.getQuantity()
                    )
            );
        }
        var paymentRequest = new PaymentRequest(
                orderRequest.getTotalAmount(),
                orderRequest.getPaymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );
        paymentClient.requestOrderPayment(paymentRequest);
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        orderRequest.getReference(),
                        orderRequest.getTotalAmount(),
                        orderRequest.getPaymentMethod(),
                        customer,
                        purchasedProducts
                )
        );

    return mapper.orderToOrderResponse(order);

    }

    public List<OrderResponse> findAll(){
        return repository.findAll().stream().map(mapper::orderToOrderResponse).collect(Collectors.toList());
    }

    public OrderResponse findById(Integer id){
        return repository.findById(id).map(mapper::orderToOrderResponse).orElseThrow(()->new IllegalArgumentException("Order not found") );
    }
}

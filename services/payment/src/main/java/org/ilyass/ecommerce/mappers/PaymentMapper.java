package org.ilyass.ecommerce.mappers;

import org.ilyass.ecommerce.dtos.PaymentRequest;
import org.ilyass.ecommerce.entities.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {


    public Payment paymentRequestToPayment(PaymentRequest paymentRequest) {
        return Payment.builder()
                .id(paymentRequest.getId())
                .amount(paymentRequest.getAmount())
                .paymentMethod(paymentRequest.getPaymentMethod())
                .orderId(paymentRequest.getOrderId())
                .build();

    }
}

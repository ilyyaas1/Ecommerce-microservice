package org.ilyass.ecommerce.kafka;


import org.ilyass.ecommerce.dtos.CustomerResponse;
import org.ilyass.ecommerce.dtos.PurchaseResponse;
import org.ilyass.ecommerce.entities.Enums.PaymentMethod;


import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
){

}

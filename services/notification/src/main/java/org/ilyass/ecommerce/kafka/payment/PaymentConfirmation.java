package org.ilyass.ecommerce.kafka.payment;

import java.math.BigDecimal;

public record PaymentConfirmation(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerId,
        String customerFirstname,
        String customerLastname,
        String customerEmail
) {
}

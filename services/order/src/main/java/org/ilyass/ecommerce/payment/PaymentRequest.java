package org.ilyass.ecommerce.payment;

import lombok.*;
import org.ilyass.ecommerce.dtos.CustomerResponse;
import org.ilyass.ecommerce.entities.Enums.PaymentMethod;


import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class PaymentRequest {
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private Integer orderId;
    private String orderReference;
    private CustomerResponse customer;
}

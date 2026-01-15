package org.ilyass.ecommerce.dtos;

import lombok.*;
import org.ilyass.ecommerce.entities.enums.PaymentMethod;

import java.math.BigDecimal;



@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class PaymentRequest {

    private Integer id;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private Integer orderId;
    private String orderReference;
    private Customer customer;
}

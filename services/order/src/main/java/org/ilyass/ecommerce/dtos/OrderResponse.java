package org.ilyass.ecommerce.dtos;

import lombok.*;
import org.ilyass.ecommerce.entities.Enums.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    private Integer id;
    private String reference;
    private BigDecimal totalAmount;
    private PaymentMethod paymentMethod;
    private String customerId;
    private LocalDate createdDate;



}

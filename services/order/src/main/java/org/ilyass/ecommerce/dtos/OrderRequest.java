package org.ilyass.ecommerce.dtos;

import jakarta.validation.constraints.Positive;
import lombok.*;
import org.ilyass.ecommerce.entities.Enums.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private Integer id;
    private String reference;
    @Positive(message="amount must be positive")
    private BigDecimal totalAmount;

    private PaymentMethod paymentMethod;
    private String customerId;
    private List<PurchaseRequest> products;

}

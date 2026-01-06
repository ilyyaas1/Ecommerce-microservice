package org.ilyass.ecommerce.dtos;

import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseResponse {
    private Integer productId;
    private String name;

    private String description;
    private BigDecimal price;
    private double quantity;
}

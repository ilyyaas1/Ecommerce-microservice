package org.ilyass.ecommerce.dtos;

import lombok.*;

import java.math.BigDecimal;

@Builder
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductPurchaseResponse {
    private Integer productId;
    private String name;

    private String description;
    private BigDecimal price;
    private double quantity;


}

package org.ilyass.ecommerce.dtos;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;

@Builder
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    @NotNull(message = "product name is required")
    private String name;
    @NotNull(message="product description is required")
    private String description;
    @Positive(message="Available quantity should be positive")
    private double availableQuantity;
    @Positive(message = "price should be positive")
    private BigDecimal price;
    @NotNull(message = "category is required")
    private Integer categoryId;
}

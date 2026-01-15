package org.ilyass.ecommerce.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductPurchaseRequest {

    @NotNull(message = "Product is mandatory")
    private Integer productId;

    @NotNull(message="quantity is mandatory")
    private double quantity;
}

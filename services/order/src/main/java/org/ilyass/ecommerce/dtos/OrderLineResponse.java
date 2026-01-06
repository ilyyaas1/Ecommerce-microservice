package org.ilyass.ecommerce.dtos;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineResponse {

    private Integer id;
    private Integer productId;
    private double quantity;

}

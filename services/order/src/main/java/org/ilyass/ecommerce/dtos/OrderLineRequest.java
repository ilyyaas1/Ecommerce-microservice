package org.ilyass.ecommerce.dtos;

import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineRequest {

    private Integer id;
    private Integer orderId;
    private Integer productId;
    private double quantity;





}

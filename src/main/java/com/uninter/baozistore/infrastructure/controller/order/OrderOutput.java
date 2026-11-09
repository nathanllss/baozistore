package com.uninter.baozistore.infrastructure.controller.order;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Builder
public class OrderOutput {

    private Long id;
    private Long customerId;
    private Long productId;
    private Integer quantity;
    private BigDecimal totalValue;
}

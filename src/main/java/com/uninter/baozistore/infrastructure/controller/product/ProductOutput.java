package com.uninter.baozistore.infrastructure.controller.product;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Builder
public class ProductOutput {

    private Long id;
    private String name;
    private BigDecimal price;
    private Boolean inStock;
}

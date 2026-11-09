package com.uninter.baozistore.infrastructure.controller.order;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderInput {

    @NotNull
    private Long customerId;
    @NotNull
    private Long productId;
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;
}

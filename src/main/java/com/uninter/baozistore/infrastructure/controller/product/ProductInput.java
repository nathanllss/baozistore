package com.uninter.baozistore.infrastructure.controller.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductInput {

    @NotBlank
    private String name;
    @NotNull
    private BigDecimal price;
    @Min(value = 0, message = "Price must be greater than or equal to zero")
    private Integer quantityInStock;
}

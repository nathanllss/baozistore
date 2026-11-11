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
    @Min(value = 0, message = "Price must be at least 0")
    private BigDecimal price;
    @NotNull
    private Boolean inStock;
}

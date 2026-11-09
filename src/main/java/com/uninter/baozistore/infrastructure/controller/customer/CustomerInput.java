package com.uninter.baozistore.infrastructure.controller.customer;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CustomerInput {

    @NotBlank
    private String name;
}

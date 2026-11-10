package com.uninter.baozistore.infrastructure.controller.customer;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Builder
public class CustomerOutput {

    private Long id;
    private String name;
    private LocalDate customerSince;
}

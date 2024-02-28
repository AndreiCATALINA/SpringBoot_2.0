package com.example.testshop.dto;

public record OrderDTO(
        Long id,
        String name,
        double total,
        Integer quantity
) {
}

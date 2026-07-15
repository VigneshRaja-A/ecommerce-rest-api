package com.example.E_Commerce.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponseDto {
    private Long id;
    private String productName;
    private Double price;
    private int stockQuantity;
}

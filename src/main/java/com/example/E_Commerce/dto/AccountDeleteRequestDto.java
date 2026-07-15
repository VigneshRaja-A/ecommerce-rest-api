package com.example.E_Commerce.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AccountDeleteRequestDto {
    @NotBlank(message = "Password is required")
    private String password;
}

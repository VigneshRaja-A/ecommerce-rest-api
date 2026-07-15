package com.example.E_Commerce.dto;

import com.example.E_Commerce.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class UserResponseDto {
    private String email;
    private Long id;
    private Role role;
}

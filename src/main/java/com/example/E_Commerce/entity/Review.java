package com.example.E_Commerce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Review
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email(message = "Please enter a valid email address")
    @NotBlank(message = "Email is required")
    private String email;
    @NotNull(message = "rating is required")
    @Min(1)
    @Max(5)
    private Integer rating;
    @NotBlank(message = "Review is required")
    private String comment;
    @NotNull(message = "Product id is required")
    private Long productId;
}

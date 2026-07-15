package com.example.E_Commerce.controller;

import com.example.E_Commerce.dto.LoginRequestDto;
import com.example.E_Commerce.dto.RegisterRequestDto;
import com.example.E_Commerce.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> registerNewUser(@Valid @RequestBody RegisterRequestDto registerRequestDTO)
    {
        authService.registerNewUser(registerRequestDTO);
        return ResponseEntity.ok("User registered successfully!!");
    }
    @PostMapping("/login")
    public ResponseEntity<String> logIn(@Valid @RequestBody LoginRequestDto loginRequestDto)
    {
           return ResponseEntity.ok(authService.login(loginRequestDto));
    }
}

package com.example.E_Commerce.controller;

import com.example.E_Commerce.dto.LoginDto;
import com.example.E_Commerce.entity.Users;
import com.example.E_Commerce.service.UserService;
import com.example.E_Commerce.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Users user)
    {
        userService.registerNewUser(user);
        return ResponseEntity.ok("User registered successfully!!");
    }
    @PostMapping("/login")
    public ResponseEntity<String> logIn(@RequestBody LoginDto loginDto)
    {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getUsername(),loginDto.getPassword())
            );
           return ResponseEntity.ok(jwtUtil.generateJwtToken(loginDto.getUsername()));
    }
}

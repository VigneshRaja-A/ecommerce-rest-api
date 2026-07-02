package com.example.E_Commerce.controller;

import com.example.E_Commerce.entity.Users;
import com.example.E_Commerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Users user)
    {
        userService.registerNewUser(user);
        return ResponseEntity.ok("User registered successfully!!");
    }
    @GetMapping("/Login")
    public ResponseEntity<String> logIn()
    {
        return ResponseEntity.ok("You have Logged in already");
    }
}

package com.example.E_Commerce.controller;

import com.example.E_Commerce.dto.AccountDeleteRequestDto;
import com.example.E_Commerce.dto.UserResponseDto;
import com.example.E_Commerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class UserController {
    @Autowired
    UserService userService;

    @PostAuthorize("returnObject.body.email == authentication.name")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long id)
    {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/delete")
   public ResponseEntity<String> deleteUser(@RequestBody AccountDeleteRequestDto accountDeleteRequestDto)
    {
        userService.deleteUser(accountDeleteRequestDto);
        return ResponseEntity.ok("User deleted Successfully");
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/")
    public ResponseEntity<String> deleteUser()
    {
        userService.deleteUserByJWT();
        return ResponseEntity.ok("Your Account deleted Successfully");
    }
}

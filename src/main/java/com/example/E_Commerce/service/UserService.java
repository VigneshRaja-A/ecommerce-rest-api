package com.example.E_Commerce.service;

import com.example.E_Commerce.dto.AccountDeleteRequestDto;
import com.example.E_Commerce.dto.UserResponseDto;
import com.example.E_Commerce.entity.Users;
import com.example.E_Commerce.exception.ItemNotFoundException;
import com.example.E_Commerce.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Transactional(readOnly = true)
    public UserResponseDto getUser(Long id) {
        Users user =  userDetailsRepository.findById(id)
                .orElseThrow(()-> new ItemNotFoundException("User not found"));
        return UserResponseDto.builder()
                .id(user.getId())
                .email(user.getUsername())
                .role(user.getRole())
                .build();

    }

    @Transactional
    public void deleteUser(AccountDeleteRequestDto accountDeleteRequestDto)
    {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,accountDeleteRequestDto.getPassword()));
       userDetailsRepository.deleteByUsername(email);
    }

    @Transactional
    public void deleteUserByJWT() {
       String email = SecurityContextHolder.getContext().getAuthentication().getName();
       userDetailsRepository.deleteByUsername(email);
    }
}

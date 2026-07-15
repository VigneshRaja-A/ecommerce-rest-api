package com.example.E_Commerce.service;

import com.example.E_Commerce.dto.UserResponseDto;
import com.example.E_Commerce.entity.Users;
import com.example.E_Commerce.enums.Role;
import com.example.E_Commerce.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    
    @Autowired
   private UserDetailsRepository userDetailsRepository;
    
    public Page<UserResponseDto> getUsersDetails(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Users> usersPage = userDetailsRepository.findByRole(Role.USER,pageable);
       return usersPage.map(users ->
               UserResponseDto.builder()
                       .email(users.getUsername())
                       .role(users.getRole())
                       .id(users.getId())
                       .build()
       );
    }
}

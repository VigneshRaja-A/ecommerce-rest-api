package com.example.E_Commerce.service;

import com.example.E_Commerce.dto.LoginRequestDto;
import com.example.E_Commerce.dto.RegisterRequestDto;
import com.example.E_Commerce.entity.Users;
import com.example.E_Commerce.enums.Role;
import com.example.E_Commerce.exception.UserAlreadyExistsException;
import com.example.E_Commerce.repository.UserDetailsRepository;
import com.example.E_Commerce.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    JWTUtil jwtUtil;

    @Autowired
    @Lazy
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public  void registerNewUser(RegisterRequestDto registerRequestDTO)
    {
        if(userDetailsRepository.existsByUsername(registerRequestDTO.getEmail()))
        {
            throw new UserAlreadyExistsException("Email already registered");
        }
        Users newUser = new Users();
        String encodedPassword = passwordEncoder.encode(registerRequestDTO.getPassword());
        newUser.setUsername(registerRequestDTO.getEmail().toLowerCase());
        newUser.setPassword(encodedPassword);
        if(registerRequestDTO.getRole()==null)
        {
            newUser.setRole(Role.USER);
        }
        else
        {
            newUser.setRole(registerRequestDTO.getRole());

        }
        userDetailsRepository.save(newUser);
    }

    public void processOAuth2Login(String email)
    {
        if(!userDetailsRepository.existsByUsername(email))
        {
            Users user = new Users();
            user.setUsername(email);
            user.setPassword(UUID.randomUUID().toString());
            user.setRole(Role.USER);
            userDetailsRepository.save(user);
        }
    }

    public String login(LoginRequestDto loginRequestDto)
    {
        String email = loginRequestDto.getEmail().toLowerCase();
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, loginRequestDto.getPassword())
        );
        return jwtUtil.generateJwtToken(email);
    }
}

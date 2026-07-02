package com.example.E_Commerce.service;

import com.example.E_Commerce.entity.Users;
import com.example.E_Commerce.repositary.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserDetailsRepository userDetailsRepositary;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public  Users registerNewUser(Users newUser)
    {
        String encodedPassword = passwordEncoder.encode(newUser.getPassword());
        newUser.setPassword(encodedPassword);

        if(newUser.getRole()== null)
        {
            newUser.setRole("ROLE_USER");
        }
         return userDetailsRepositary.save(newUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDetailsRepositary.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }
}

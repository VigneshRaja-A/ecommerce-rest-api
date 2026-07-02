package com.example.E_Commerce.repositary;

import com.example.E_Commerce.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository<Users,Long> {

    public Optional<Users> findByUsername(String username);
}

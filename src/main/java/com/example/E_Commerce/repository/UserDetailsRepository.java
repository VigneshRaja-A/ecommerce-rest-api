package com.example.E_Commerce.repository;

import com.example.E_Commerce.entity.Users;
import com.example.E_Commerce.enums.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository<Users,Long> {

    Optional<Users> findByUsername(String email);

     boolean existsByUsername(String email);

     void deleteByUsername(String email);

     Page<Users> findByRole(Role role, Pageable pageable);
}

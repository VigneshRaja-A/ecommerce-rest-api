package com.example.E_Commerce.repository;

import com.example.E_Commerce.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long>
{

    boolean existsByEmailAndProductId(String email,Long productId);
}


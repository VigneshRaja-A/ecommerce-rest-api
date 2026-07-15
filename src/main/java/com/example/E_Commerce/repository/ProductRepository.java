package com.example.E_Commerce.repository;

import com.example.E_Commerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ProductRepository extends JpaRepository<Product,Long>
{

    Page<Product> findByStockQuantity(int stockQuantity,Pageable pageable);

    Page<Product> findByCategory(String category, Pageable pageable);

    Page<Product> findByIsactiveFalse(Pageable pageable);

    boolean existsByName(String name);
}

package com.example.E_Commerce.repositary;

import com.example.E_Commerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepositary extends JpaRepository<Product,Long>
{
    @Query(nativeQuery = true,value = "SELECT * FROM PRODUCT WHERE stock_quantity = 0")
    public List<Product> getOutOfStockItems();

    public List<Product> findByCategory(String category);
}

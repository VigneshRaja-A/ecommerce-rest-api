package com.example.E_Commerce.service;

import com.example.E_Commerce.entity.Product;
import com.example.E_Commerce.repositary.ProductRepositary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService
{
    @Autowired
    private ProductRepositary productRepo;


    public Page<Product> getAllProducts(int page, int size)
    {
        Pageable pageable = PageRequest.of(page,size);
        return productRepo.findAll(pageable);
    }

    public Product addProduct(Product product)
    {

        return productRepo.save(product);
    }

    public Optional<Product> updateProduct(Long id,Product updatedDetails)
    {
        return productRepo.findById(id)
                .map(existingProduct -> {
                    existingProduct.setName(updatedDetails.getName());
                    existingProduct.setPrice(updatedDetails.getPrice());
                    existingProduct.setCategory(updatedDetails.getCategory());
                    existingProduct.setStockQuantity(updatedDetails.getStockQuantity());

                    return productRepo.save(existingProduct);
                });
    }

    public Optional<Product> deleteProduct(Long id)
    {
       Optional<Product> product = productRepo.findById(id);
       if(product.isPresent())
       {
           productRepo.deleteById(id);
       }
       return product;
    }

    public List<Product> findByCategory(String category)
    {
       return productRepo.findByCategory(category);
    }

    public List<Product> getOutOfStockItems()
    {
       return productRepo.getOutOfStockItems();
    }

    public Optional<Product> getById(Long id)
    {
        return productRepo.findById(id);
    }
}

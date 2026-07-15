package com.example.E_Commerce.service;

import com.example.E_Commerce.dto.ProductResponseDto;
import com.example.E_Commerce.entity.Product;
import com.example.E_Commerce.exception.ItemNotFoundException;
import com.example.E_Commerce.exception.ProductAlreadyExistsException;
import com.example.E_Commerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class ProductService
{
    @Autowired
    private ProductRepository productRepo;

    @Transactional(readOnly = true)
    public Page<ProductResponseDto> getAllProducts(int page, int size)
    {
        Pageable pageable = PageRequest.of(page,size);
        Page<Product> product = productRepo.findAll(pageable);
        return product.map(products -> ProductResponseDto.builder()
                .id(products.getId())
                .productName(products.getName())
                .price(products.getPrice())
                .stockQuantity(products.getStockQuantity())
                .build());
    }

    public Product addProduct(Product product)
    {
        if(productRepo.existsByName(product.getName()))
        {
            throw  new ProductAlreadyExistsException(product.getName()+" already exists!");
        }
        product.setIsactive(true);
        return productRepo.save(product);
    }

    @Transactional
    public Product updateProduct(Long id,Product updatedDetails)
    {
        Product existingProduct = productRepo.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Product id: "+id+" not found"));
        String updatedDetailsName = updatedDetails.getName();
        if(!existingProduct.getName().equals(updatedDetailsName))
        {
            if(productRepo.existsByName(updatedDetailsName))
            {
                throw new ProductAlreadyExistsException("The name "+updatedDetailsName+" is already taken");
            }
            existingProduct.setName(updatedDetailsName);
        }
        if(!existingProduct.isIsactive())
        {
            existingProduct.setIsactive(true);
        }
        existingProduct.setPrice(updatedDetails.getPrice());
        existingProduct.setCategory(updatedDetails.getCategory());
        existingProduct.setStockQuantity(updatedDetails.getStockQuantity());
        return existingProduct;
    }

    @Transactional
    public Product deleteProduct(Long id)
    {
        Product product = productRepo.findById(id)
                .orElseThrow( () -> new ItemNotFoundException("Product id: "+id+" not found"));
        product.setIsactive(false);
        return product;
    }

    @Transactional(readOnly = true)
    public Page<ProductResponseDto> findByCategory(String category,int page,int size)
    {
        Pageable pageable = PageRequest.of(page,size);
        Page<Product> products = productRepo.findByCategory(category,pageable);
        return products.map(product -> ProductResponseDto.builder()
                .id(product.getId())
                .productName(product.getName())
                .price(product.getPrice())
                .stockQuantity(product.getStockQuantity())
                .build());
    }

    @Transactional(readOnly = true)
    public Page<ProductResponseDto> getOutOfStockItems(int page, int size)
    {
        Pageable pageable = PageRequest.of(page,size);
        Page<Product> products = productRepo.findByStockQuantity(0,pageable);
       return products.map(product -> ProductResponseDto.builder()
               .id(product.getId())
               .productName(product.getName())
               .stockQuantity(product.getStockQuantity())
               .build());
    }

    @Transactional(readOnly = true)
    public ProductResponseDto getById(Long id)
    {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Product not found"));
        return ProductResponseDto.builder()
                .id(product.getId())
                .productName(product.getName())
                .price(product.getPrice())
                .stockQuantity(product.getStockQuantity())
                .build();
    }

    @Transactional(readOnly = true)
    public Page<ProductResponseDto> getInActiveProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Product> products = productRepo.findByIsactiveFalse(pageable);
        return products.map(product -> ProductResponseDto.builder()
                .id(product.getId())
                .productName(product.getName())
                .price(product.getPrice())
                .build());
    }

    public boolean isExistByProductId(Long id)
    {
        return productRepo.existsById(id);
    }
}

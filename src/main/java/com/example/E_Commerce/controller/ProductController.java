package com.example.E_Commerce.controller;

import com.example.E_Commerce.dto.ProductResponseDto;
import com.example.E_Commerce.entity.Product;
import com.example.E_Commerce.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController
{
    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getById(@PathVariable Long id)
    {
        return ResponseEntity.ok(productService.getById(id));
    }


    @GetMapping("")
    public ResponseEntity<Page<ProductResponseDto>> getAllProducts(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "10") int size )
    {
        return ResponseEntity.ok(productService.getAllProducts(page,size));
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<ProductResponseDto>> getByProductCategory(@RequestParam String category,@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "10") int size)
    {
       return ResponseEntity.ok(productService.findByCategory(category,page,size));
    }

    @GetMapping("/outofstock")
    public ResponseEntity<Page<ProductResponseDto>> getOutOfStockItems(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "10") int size)
    {
        return ResponseEntity.ok(productService.getOutOfStockItems(page,size));
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/deleted")
    public ResponseEntity<Page<ProductResponseDto>> getInActiveProducts(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "10") int size)
    {
        return ResponseEntity.ok(productService.getInActiveProducts(page,size));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity<Product> addProduct(@Valid @RequestBody Product product)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(product));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @Valid @RequestBody Product updatedDetails)
    {
        return ResponseEntity.ok(productService.updateProduct(id,updatedDetails));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public  ResponseEntity<Product> deleteProduct(@PathVariable Long id)
    {
        return ResponseEntity.ok(productService.deleteProduct(id));
    }

}

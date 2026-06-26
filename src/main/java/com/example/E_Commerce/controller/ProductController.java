package com.example.E_Commerce.controller;

import com.example.E_Commerce.entity.Product;
import com.example.E_Commerce.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController
{
    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id)
    {
        return productService.getById(id)
                .map(product -> ResponseEntity.ok(product))
                .orElseGet(() ->ResponseEntity.notFound().build());
    }


    @GetMapping("")
    public ResponseEntity<Page<Product>> getAllProducts(@RequestParam int page,@RequestParam int size )
    {
        return ResponseEntity.ok(productService.getAllProducts(page,size));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Product>> getByProductCategory(@RequestParam String category)
    {
       List<Product> products = productService.findByCategory(category);
       return products.isEmpty()?  ResponseEntity.notFound().build() :  ResponseEntity.ok(products);

    }
    @GetMapping("/outofstock")
    public ResponseEntity<List<Product>> getOutOfStockItems()
    {
        return ResponseEntity.ok(productService.getOutOfStockItems());
    }

    @PostMapping("")
    public ResponseEntity<Product> addProduct(@Valid @RequestBody Product product)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @Valid @RequestBody Product updatedDetails)
    {
        return productService.updateProduct(id,updatedDetails)
                .map(product ->ResponseEntity.ok(product))
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Product> deletePrduct(@PathVariable Long id)
    {
        return productService.deleteProduct(id)
                .map(product ->ResponseEntity.ok(product))
                .orElseGet(()-> ResponseEntity.notFound().build());
    }
}

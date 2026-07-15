package com.example.E_Commerce.controller;

import com.example.E_Commerce.entity.Review;
import com.example.E_Commerce.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ReviewController
{
    @Autowired
    private ReviewService reviewService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/review")
    public ResponseEntity<Review> writeAReview(@Valid @RequestBody Review review)
    {
     return ResponseEntity.ok(reviewService.writeAReview(review));
    }

    @PreAuthorize("hasAuthority('READ')")
    @GetMapping("/reviews")
    public ResponseEntity<Page<Review>> getAllReviews(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "10") int size)
    {
        return ResponseEntity.ok(reviewService.getAllReviews(page,size));
    }
}

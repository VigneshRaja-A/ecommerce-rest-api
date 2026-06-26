package com.example.E_Commerce.controller;

import com.example.E_Commerce.entity.Review;
import com.example.E_Commerce.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ReviewController
{
    @Autowired
    private ReviewService reviewService;

    @PostMapping("/review")
    public ResponseEntity<Review> writeAReview(@Valid @RequestBody Review review)
    {
     return reviewService.writeAReview(review)
             .map(body -> ResponseEntity.ok(body))
             .orElseGet(() ->ResponseEntity.notFound().build());
    }

    @GetMapping("/reviews")
    public ResponseEntity<Page<Review>> getAllReviews(@RequestParam int page,@RequestParam int size)
    {
        return ResponseEntity.ok(reviewService.getAllReviews(page,size));
    }
}

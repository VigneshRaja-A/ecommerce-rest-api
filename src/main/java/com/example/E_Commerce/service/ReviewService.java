package com.example.E_Commerce.service;


import com.example.E_Commerce.entity.Review;
import com.example.E_Commerce.repositary.ReviewRepository;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepo;

    @Autowired
    private ProductService productService;

    public Optional<Review> writeAReview(Review review)
    {
      if(productService.getById(review.getProductId()).isEmpty())
      {
          return Optional.empty();
      }
      return Optional.of(reviewRepo.save(review));
    }

    public Page<Review> getAllReviews(int page, int size)
    {
        Pageable pageable = PageRequest.of(page,size);
        return reviewRepo.findAll(pageable);
    }
}

package com.example.E_Commerce.service;

import com.example.E_Commerce.entity.Review;
import com.example.E_Commerce.exception.ItemNotFoundException;
import com.example.E_Commerce.exception.ReviewAlreadyExistException;
import com.example.E_Commerce.repository.ReviewRepository;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepo;

    @Autowired
    private ProductService productService;

    public Review writeAReview(Review review)
    {
        if(!productService.isExistByProductId(review.getProductId()))
        {
            throw new ItemNotFoundException("Enter a valid product id");
        }
        if(reviewRepo.existsByEmailAndProductId(review.getEmail(),review.getProductId()))
        {
            throw new ReviewAlreadyExistException("Review already given");
        }
        return reviewRepo.save(review);
      }

    public Page<Review> getAllReviews(int page, int size)
    {
        Pageable pageable = PageRequest.of(page,size);
        return reviewRepo.findAll(pageable);
    }
}

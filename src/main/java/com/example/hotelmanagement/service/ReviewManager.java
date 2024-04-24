package com.example.hotelmanagement.service;

import com.example.hotelmanagement.doa.entities.Review;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReviewManager {
    public Review addReview(Review review);
    public boolean deleteReview(Review review);
    public boolean deleteReviewById(Integer id);
    public boolean updateReview(Review review);
    public Review findReview(Review review);
    public Review findReviewById(Integer id);
    public List<Review> getAllReviews();
    public Page<Review> getAllReviews(int page, int taille);
}

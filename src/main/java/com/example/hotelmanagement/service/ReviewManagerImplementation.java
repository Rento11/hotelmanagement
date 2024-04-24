package com.example.hotelmanagement.service;

import com.example.hotelmanagement.doa.entities.Review;
import com.example.hotelmanagement.doa.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewManagerImplementation implements ReviewManager{
    @Autowired
    private ReviewRepository reviewRepository;
    @Override
    public Review addReview(Review review) {
        try{
            return reviewRepository.save(review);
        }catch(Exception exception){
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @Override
    public boolean deleteReview(Review review) {
        try{
            reviewRepository.delete(review);
            return true;
        }catch(Exception exception){
            System.out.println(exception.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteReviewById(Integer id) {
        try{
            reviewRepository.delete(reviewRepository.findById(id).get());
            return true;
        }catch(Exception exception){
            System.out.println(exception.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateReview(Review review) {
        try {
            reviewRepository.save(review);
            return true;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return false;
        }
    }

    @Override
    public Review findReview(Review review) {
        try{
            return reviewRepository.findById(review.getId()).get();
        }catch(Exception exception){
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @Override
    public Review findReviewById(Integer id) {
        try{
            return reviewRepository.findById(id).get();
        }catch(Exception exception){
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @Override
    public List<Review> getAllReviews() {
        try{
            return reviewRepository.findAll();
        }catch(Exception exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @Override
    public Page<Review> getAllReviews(int page, int taille) {
        return reviewRepository.findAll(PageRequest.of(page,taille));
    }
}

package com.example.hotelmanagement.doa.repositories;

import com.example.hotelmanagement.doa.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
}

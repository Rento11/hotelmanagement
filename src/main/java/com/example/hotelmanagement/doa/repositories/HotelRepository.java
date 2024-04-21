package com.example.hotelmanagement.doa.repositories;

import com.example.hotelmanagement.doa.entities.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {
    Page<Hotel> findHotelByNameContainsIgnoreCase(String keyword, Pageable pageable);
}

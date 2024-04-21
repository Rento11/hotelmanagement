package com.example.hotelmanagement.doa.repositories;

import com.example.hotelmanagement.doa.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
}

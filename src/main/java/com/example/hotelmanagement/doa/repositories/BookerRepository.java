package com.example.hotelmanagement.doa.repositories;

import com.example.hotelmanagement.doa.entities.Booker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookerRepository extends JpaRepository<Booker, Integer> {
}

package com.example.hotelmanagement.doa.repositories;

import com.example.hotelmanagement.doa.entities.Booker;
import com.example.hotelmanagement.doa.entities.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookerRepository extends JpaRepository<Booker, Integer> {
}

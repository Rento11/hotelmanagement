package com.example.hotelmanagement.doa.repositories;

import com.example.hotelmanagement.doa.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}

package com.example.hotelmanagement.doa.repositories;

import com.example.hotelmanagement.doa.entities.Hotel;
import com.example.hotelmanagement.doa.entities.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    Page<Room> findRoomByRoomNumberContainingIgnoreCase(String keyword, Pageable pageable);
    Page<Room> findRoomByHotel(Hotel hotel, Pageable pageable);
//    Page<Room> findRoomByAvailableTrue( Pageable pageable);
//    Page<Room> findRoomByPricePerNightBetween(Hotel hotel, Pageable pageable);

}
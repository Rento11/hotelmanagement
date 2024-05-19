package com.example.hotelmanagement.service;

import com.example.hotelmanagement.doa.entities.Hotel;
import com.example.hotelmanagement.doa.entities.Room;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RoomManager {
    public Room addRoom(Room room);
    public boolean deleteRoom(Room room);
    public boolean deleteRoomById(Integer id);
    public boolean updateRoom(Room room);
    public Room findRoom(Room room);
    public Room findRoomById(Integer id);
    public List<Room> getAllRooms();
    public Page<Room> getAllRooms(int page, int taille);
    public Page<Room> searchRoomsByRoomNumber(String keyword, int page, int taille);
    public Page<Room> searchRoomByHotel(Hotel hotel, int page, int taille);
}

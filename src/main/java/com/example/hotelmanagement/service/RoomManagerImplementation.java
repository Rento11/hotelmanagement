package com.example.hotelmanagement.service;

import com.example.hotelmanagement.doa.entities.Hotel;
import com.example.hotelmanagement.doa.entities.Room;
import com.example.hotelmanagement.doa.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomManagerImplementation implements RoomManager{

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Room addRoom(Room room) {
        try{
            return roomRepository.save(room);
        }catch(Exception exception){
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @Override
    public boolean deleteRoom(Room room) {
        try{
            roomRepository.delete(room);
            return true;
        }catch(Exception exception){
            System.out.println(exception.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteRoomById(Integer id) {
        try{
            roomRepository.delete(roomRepository.findById(id).get());
            return true;
        }catch(Exception exception){
            System.out.println(exception.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateRoom(Room room) {
        try{
            roomRepository.save(room);
            return true;
        }catch(Exception exception){
            System.out.println(exception.getMessage());
            return false;
        }
    }

    @Override
    public Room findRoom(Room room) {
        try{
            return roomRepository.findById(room.getId()).get();
        }catch(Exception exception){
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @Override
    public Room findRoomById(Integer id) {
        try{
            return roomRepository.findById(id).get();
        }catch(Exception exception){
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @Override
    public List<Room> getAllRooms() {
        try{
            return roomRepository.findAll();
        }catch(Exception exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @Override
    public Page<Room> getAllRooms(int page, int taille) {
        return roomRepository.findAll(PageRequest.of(page,taille));
    }

    @Override
    public Page<Room> searchRoomsByRoomNumber(String keyword, int page, int taille) {
        return roomRepository.findRoomByRoomNumberContainingIgnoreCase(keyword, PageRequest.of(page, taille));
    }

    @Override
    public Page<Room> searchRoomByHotel(Hotel hotel, int page, int taille) {
        return roomRepository.findRoomByHotel(hotel,PageRequest.of(page,taille));
    }
}

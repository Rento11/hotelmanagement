package com.example.hotelmanagement.service;

import com.example.hotelmanagement.doa.entities.Hotel;
import org.springframework.data.domain.Page;

import java.util.List;

public interface HotelManager {
    public Hotel addHotel(Hotel hotel);
    public boolean deleteHotel(Hotel hotel);
    public boolean deleteHotelById(Integer id);
    public boolean updateHotel(Hotel hotel);
    public Hotel findHotel(Hotel hotel);
    public Hotel findHotelById(Integer id);
    public List<Hotel> getAllHotels();
    public Page<Hotel> getAllHotels(int page, int taille);
    public Page<Hotel> searchHotelsByName(String keyword, int page, int taille);
}

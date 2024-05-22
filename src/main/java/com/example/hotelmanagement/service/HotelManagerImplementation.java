package com.example.hotelmanagement.service;


import com.example.hotelmanagement.config.UserEntity;
import com.example.hotelmanagement.config.UserRepository;
import com.example.hotelmanagement.doa.entities.Hotel;
import com.example.hotelmanagement.doa.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelManagerImplementation implements HotelManager {

    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Hotel addHotel(Hotel hotel) {
        try {
            return hotelRepository.save(hotel);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @Override
    public boolean deleteHotel(Hotel hotel) {
        try {
            hotelRepository.delete(hotel);
            return true;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteHotelById(Integer id) {
        try {
            hotelRepository.delete(hotelRepository.findById(id).get());
            return true;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateHotel(Hotel hotel) {
        try {
            hotelRepository.save(hotel);
            return true;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return false;
        }
    }

    @Override
    public Hotel findHotel(Hotel hotel) {
        try {
            return hotelRepository.findById(hotel.getId()).get();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @Override
    public Hotel findHotelById(Integer id) {
        try {
            return hotelRepository.findById(id).get();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @Override
    public List<Hotel> getAllHotels() {
        try {
            return hotelRepository.findAll();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @Override
    public Page<Hotel> getAllHotels(int page, int taille) {
        return hotelRepository.findAll(PageRequest.of(page, taille));
    }

    @Override
    public Page<Hotel> searchHotelsByName(String keyword, int page, int taille) {
        return hotelRepository.findHotelByNameContainsIgnoreCase(keyword, PageRequest.of(page, taille));
    }
}

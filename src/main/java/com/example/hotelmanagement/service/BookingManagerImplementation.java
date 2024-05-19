package com.example.hotelmanagement.service;

import com.example.hotelmanagement.doa.entities.Booking;
import com.example.hotelmanagement.doa.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingManagerImplementation implements BookingManager{
    @Autowired
    private BookingRepository bookingRepository;
    @Override
    public Booking addBooking(Booking booking) {
        try{
            return bookingRepository.save(booking);
        }catch(Exception exception){
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @Override
    public boolean deleteBooking(Booking booking) {
        try{
            bookingRepository.delete(booking);
            return true;
        }catch(Exception exception){
            System.out.println(exception.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteBookingById(Integer id) {
        try{
            bookingRepository.delete(bookingRepository.findById(id).get());
            return true;
        }catch(Exception exception){
            System.out.println(exception.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateBooking(Booking booking) {
        try {
            bookingRepository.save(booking);
            return true;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return false;
        }
    }

    @Override
    public Booking findBooking(Booking booking) {
        try{
            return bookingRepository.findById(booking.getId()).get();
        }catch(Exception exception){
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @Override
    public Booking findBookingById(Integer id) {
        try{
            return bookingRepository.findById(id).get();
        }catch(Exception exception){
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @Override
    public List<Booking> getAllBookings() {
        try{
            return bookingRepository.findAll();
        }catch(Exception exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @Override
    public Page<Booking> getAllBookings(int page, int taille) {
        return bookingRepository.findAll(PageRequest.of(page,taille));
    }

    @Override
    public Page<Booking> searchBookingsByPrice(double price, int page, int taille) {
        return bookingRepository.findBookingByTotalPriceBefore(price, PageRequest.of(page, taille));
    }
}

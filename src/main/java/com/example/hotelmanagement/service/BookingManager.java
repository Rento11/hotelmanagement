package com.example.hotelmanagement.service;

import com.example.hotelmanagement.doa.entities.Booking;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookingManager {
    public Booking addBooking(Booking booking);
    public boolean deleteBooking(Booking booking);
    public boolean deleteBookingById(Integer id);
    public boolean updateBooking(Booking booking);
    public Booking findBooking(Booking booking);
    public Booking findBookingById(Integer id);
    public List<Booking> getAllBookings();
    public Page<Booking> getAllBookings(int page, int taille);
    public Page<Booking> searchBookingsByPrice(double price, int page, int taille);
}

package com.example.hotelmanagement;

import com.example.hotelmanagement.doa.entities.*;
import com.example.hotelmanagement.service.*;
import com.example.hotelmanagement.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class HotelmanagementApplication {

    @Autowired
    HotelManager hotelManager;
    @Autowired
    RoomManager roomManager;
    @Autowired
    BookerManager bookerManager;
    @Autowired
    BookingManager bookingManager;
    @Autowired
    ReviewManager reviewManager;

    public static void main(String[] args) {
        SpringApplication.run(HotelmanagementApplication.class, args);
    }

    @Bean
    public CommandLineRunner start() {
        return args -> {
            Booker booker1 = new Booker();
            Booker booker2 = new Booker();
            bookerManager.addBooker(booker1);
            bookerManager.addBooker(booker2);
            Booking booking1 = new Booking(null, LocalDate.of(2000, 12, 12), LocalDate.of(2001, 12, 12), 12.0, null);
            Booking booking2 = new Booking(null, LocalDate.of(2001, 12, 12), LocalDate.of(2002, 12, 12), 13.0, null);
            Booking booking3 = new Booking(null, LocalDate.of(2002, 12, 12), LocalDate.of(2003, 12, 12), 14.0, null);
            bookingManager.addBooking(booking1);
            bookingManager.addBooking(booking2);
            bookingManager.addBooking(booking3);
            Review review1 = new Review(null, 2, "no comment1", LocalDate.of(2020, 12, 12), null, null);
            Review review2 = new Review(null, 5, "no comment2", LocalDate.of(2020, 12, 12), null, null);
            Review review3 = new Review(null, 3, "no comment3", LocalDate.of(2020, 12, 12), null, null);
            reviewManager.addReview(review1);
            reviewManager.addReview(review2);
            reviewManager.addReview(review3);
            Hotel hotel1 = new Hotel(null, "HYATT REGENCY", "RABAT", "RABAT DOWNTOWN", "fromInside.png", null, null);
            Hotel hotel2 = new Hotel(null, "MARIOT", "TANGIER", "TANGIER DOWNTOWN", "fromInside.png", null, null);
            Hotel hotel3 = new Hotel(null, "FOUR SEASONS", "CASABLANCA", "CASABLANCA DOWNTOWN", "fromInside.png", null, null);
            Hotel hotel4 = new Hotel(null, "HILTON", "CASABLANCA", "CASABLANCA DOWNTOWN", "fromInside.png", null, null);
            Hotel hotel5 = new Hotel(null, "KENZI", "TANGIER", "TANGIER DOWNTOWN", "fromInside.png", null, null);
            Hotel hotel6 = new Hotel(null, "MAZAGAN", "ELJADIDA", "ELJADIDA DOWNTOWN", "fromInside.png", null, null);
            hotelManager.addHotel(hotel1);
            hotelManager.addHotel(hotel2);
            hotelManager.addHotel(hotel3);
            hotelManager.addHotel(hotel4);
            hotelManager.addHotel(hotel5);
            hotelManager.addHotel(hotel6);
            Room room101 = new Room(null, "101", 300.00, false, "room.png", 3, hotel1);
            Room room102 = new Room(null, "102", 2500.00, true, "room.png", 2, hotel1);
            Room room103 = new Room(null, "103", 1200.00, false, "room.png", 1, hotel1);
            Room room104 = new Room(null, "104", 1500.00, true, "room.png", 2, hotel1);
            Room room105 = new Room(null, "105", 300.00, true, "room.png", 3, hotel2);
            Room room106 = new Room(null, "106", 200.00, true, "room.png", 2, hotel2);
            Room room107 = new Room(null, "107", 1150.00, false, "room.png", 1, hotel3);
            Room room108 = new Room(null, "108", 500.00, true, "room.png", 2, null);
            roomManager.addRoom(room101);
            roomManager.addRoom(room102);
            roomManager.addRoom(room103);
            roomManager.addRoom(room104);
            roomManager.addRoom(room105);
            roomManager.addRoom(room106);
            roomManager.addRoom(room107);
            roomManager.addRoom(room108);

        };
    }

    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            storageService.init();
        };
    }


}

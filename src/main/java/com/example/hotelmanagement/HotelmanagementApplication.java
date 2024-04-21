package com.example.hotelmanagement;

import com.example.hotelmanagement.doa.entities.Hotel;
import com.example.hotelmanagement.doa.entities.Room;
import com.example.hotelmanagement.service.HotelManager;
import com.example.hotelmanagement.service.RoomManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HotelmanagementApplication {

    @Autowired
    HotelManager hotelManager;
    @Autowired
    RoomManager roomManager;

    public static void main(String[] args) {
        SpringApplication.run(HotelmanagementApplication.class, args);
    }

    @Bean
    public CommandLineRunner start(){
        return args -> {
            Room room101 = new Room(null,"101",300.00,true,3,null);
            Room room102 = new Room(null,"102",2500.00,true,2,null);
            Room room103 = new Room(null,"103",1200.00,false,1,null);
            Room room104 = new Room(null,"104",1500.00,true,2,null);
            Room room105 = new Room(null,"105",300.00,true,3,null);
            Room room106 = new Room(null,"106",200.00,true,2,null);
            Room room107 = new Room(null,"107",1150.00,false,1,null);
            Room room108 = new Room(null,"108",500.00,true,2,null);
            roomManager.addRoom(room101);
            roomManager.addRoom(room102);
            roomManager.addRoom(room103);
            roomManager.addRoom(room104);
            roomManager.addRoom(room105);
            roomManager.addRoom(room106);
            roomManager.addRoom(room107);
            roomManager.addRoom(room108);
            Hotel hotel1 = new Hotel(null,"HYATT REGENCY","RABAT","RABAT DOWNTOWN",null);
            Hotel hotel2 = new Hotel(null,"MARIOT","TANGIER","TANGIER DOWNTOWN",null);
            Hotel hotel3 = new Hotel(null,"FOUR SEASONS","CASABLANCA","CASABLANCA DOWNTOWN",null);
            Hotel hotel4 = new Hotel(null,"IMPERIAL","CASABLANCA","CASABLANCA DOWNTOWN",null);
            Hotel hotel5 = new Hotel(null,"KENZI","TANGIER","TANGIER DOWNTOWN",null);
            Hotel hotel6 = new Hotel(null,"MAZAGAN","ELJADIDA","ELJADIDA DOWNTOWN",null);
            hotelManager.addHotel(hotel1);
            hotelManager.addHotel(hotel2);
            hotelManager.addHotel(hotel3);
            hotelManager.addHotel(hotel4);
            hotelManager.addHotel(hotel5);
            hotelManager.addHotel(hotel6);
        };
    }
}

package com.example.hotelmanagement.web;

import com.example.hotelmanagement.doa.entities.Room;
import com.example.hotelmanagement.service.RoomManager;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RoomController {
    @Autowired
    private RoomManager roomManager;

    @GetMapping("roomsList")
    public String getRooms(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "taille", defaultValue = "3") int taille, @RequestParam(name = "search", defaultValue = "") String keyword){
        Page<Room> rooms = roomManager.searchRooms(keyword,page, taille);
        model.addAttribute("rooms", rooms.getContent());
        int[] pages = new int[rooms.getTotalPages()];
        for (int i = 0; i < pages.length; i++) {
            pages[i] = i;
        }
        model.addAttribute("pages", pages);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "roomsList";
    }

    @GetMapping("/addRoom")
    public String addRoomGet(Model model) {
        model.addAttribute("room", new Room());
        return "addRoom";
    }

    @PostMapping("/addRoom")
    public String addRoomPost(Model model, BindingResult bindingResult, @RequestParam(name = "roomNumber") String roomNumber, @RequestParam(name = "pricePerNight") double pricePerNight,@RequestParam(name = "isAvailable") boolean isAvailable,@RequestParam(name = "capacity") int capacity) {
        Room room = new Room(null,roomNumber,pricePerNight,isAvailable,capacity,null);
        if (bindingResult.hasErrors()) {
            return "addRoom";
        }
        roomManager.addRoom(room);
        return "redirect:/roomsList";
    }

    @GetMapping("/deleteRoom")
    public String deleteRoomAction(@RequestParam(name = "id") Integer id, Integer page, String search) {
        if (roomManager.deleteRoomById(id)) {
            return "redirect:/roomsList";
        } else {
            return "error";
        }
    }

    @GetMapping("/updateRoom")
    public String updateRoomGet(Model model, @RequestParam(name = "id") Integer id) {
        Room room = roomManager.findRoomById(id);
        if (room != null) {
            model.addAttribute("room", room);
            return "updateRoom";
        } else {
            return "error";
        }
    }

    @PostMapping("/updateRoom")
    public String updateRoomPost(Model model, @RequestParam(name = "id") Integer id, @RequestParam(name = "roomNumber") String roomNumber, @RequestParam(name = "pricePerNight") double pricePerNight, @RequestParam(name = "isAvailable") boolean isAvailable, @RequestParam(name = "capacity") int capacity) {
        Room room = roomManager.findRoomById(id);
        room.setRoomNumber(roomNumber);
        room.setPricePerNight(pricePerNight);
        room.setAvailable(isAvailable);
        room.setCapacity(capacity);
        if (room != null) {
            roomManager.updateRoom(room);
            return "redirect:/roomsList";
        } else {
            return "error";
        }
    }
}

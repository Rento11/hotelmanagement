package com.example.hotelmanagement.web;

import com.example.hotelmanagement.doa.entities.Hotel;
import com.example.hotelmanagement.doa.entities.Room;
import com.example.hotelmanagement.service.HotelManager;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class RoomController {
    @Autowired
    private RoomManager roomManager;
    @Autowired
    private HotelManager hotelManager;

//    @GetMapping("roomsList")
//    public String getRooms(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "taille", defaultValue = "3") int taille, @RequestParam(name = "search", defaultValue = "") String keyword){
//        Page<Room> rooms = roomManager.searchRoomsByRoomNumber(keyword,page, taille);
//        model.addAttribute("rooms", rooms.getContent());
//        int[] pages = new int[rooms.getTotalPages()];
//        for (int i = 0; i < pages.length; i++) {
//            pages[i] = i;
//        }
//        model.addAttribute("pages", pages);
//        model.addAttribute("currentPage", page);
//        model.addAttribute("keyword", keyword);
//        return "roomsList";
//    }


    @GetMapping("roomsList")
    public String getRooms(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "taille", defaultValue = "6") int taille, @RequestParam(name = "idHotel") Integer idHotel) {
        Hotel hotel = hotelManager.findHotelById(idHotel);
        Page<Room> rooms = roomManager.searchRoomByHotel(hotel, page, taille);
        model.addAttribute("rooms", rooms.getContent());
        int[] pages = new int[rooms.getTotalPages()];
        for (int i = 0; i < pages.length; i++) {
            pages[i] = i;
        }
        model.addAttribute("pages", pages);
        model.addAttribute("currentPage", page);
        model.addAttribute("idHotel", idHotel);
        return "roomsList";
    }

    @GetMapping("/addRoom")
    public String addRoomGet(Model model, @RequestParam(name = "idHotel") Integer id) {
        model.addAttribute("room", new Room());
        Hotel hotel = hotelManager.findHotelById(id);
        model.addAttribute("hotel", hotel);
        return "addRoom";
    }

    @PostMapping("/addRoom")
    public String addRoomPost(Model model,
                              @Valid Room room,
                              @RequestParam(name = "isAvailable") boolean isAvailable,
                              @RequestParam(name = "idHotel") Integer idHotel,
                              @RequestParam(name="imageFileName")MultipartFile imageFileName,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addRoom";
        }
        Hotel hotel = hotelManager.findHotelById(idHotel);
        room.setHotel(hotel);
        room.setAvailable(isAvailable);

        // Handle image file upload
        if (imageFileName != null && !imageFileName.isEmpty()) {
            try {
                // Save the image file
                String fileName = "Upload_" + imageFileName.getOriginalFilename();
                String uploadPath = "/static/img";

                // Ensure the directory exists
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                // Save the image file
                File uploadedFile = new File(uploadPath +"/"+ fileName);
                imageFileName.transferTo(uploadedFile);

                // Assuming 'book' is an instance of a class that has a method to set the image path
                room.setImageFileName(fileName);
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("errorMessage", "Error uploading the file.");
                // Handle file upload error
            }
        }

        roomManager.addRoom(room);
        return "redirect:/roomsList?idHotel=" + idHotel;
    }

    @GetMapping("/deleteRoom")
    public String deleteRoomAction(@RequestParam(name = "idHotel") Integer idHotel, @RequestParam(name = "idRoom") Integer idRoom) {
        if (roomManager.deleteRoomById(idRoom)) {
            return "redirect:/roomsList?idHotel=" + idHotel;
        } else {
            return "error";
        }
    }

    @GetMapping("/updateRoom")
    public String updateRoomGet(Model model,
                                @RequestParam(name = "idRoom") Integer idRoom,
                                @RequestParam(name = "idHotel") Integer idHotel) {

        Room room = roomManager.findRoomById(idRoom);
        Hotel hotel = hotelManager.findHotelById(idHotel);
        if (room != null && hotel != null) {
            model.addAttribute("room", room);
            model.addAttribute("hotel", hotel);
            return "updateRoom";
        } else {
            return "error";
        }
    }

    @PostMapping("/updateRoom")
    public String updateRoomPost(@RequestParam(name = "idHotel") Integer idHotel,
                                 @RequestParam(name = "idRoom") Integer idRoom,
                                 @RequestParam(name = "roomNumber") String roomNumber,
                                 @RequestParam(name = "pricePerNight") double pricePerNight,
                                 @RequestParam(name = "isAvailable") boolean isAvailable,
                                 @RequestParam(name = "capacity") int capacity) {

        Room room = roomManager.findRoomById(idRoom);
        room.setRoomNumber(roomNumber);
        room.setPricePerNight(pricePerNight);
        room.setAvailable(isAvailable);
        room.setCapacity(capacity);
        room.setHotel(hotelManager.findHotelById(idHotel));

        System.out.println(isAvailable);

        if (room != null) {
            roomManager.updateRoom(room);
            return "redirect:/roomsList?idHotel=" + idHotel;
        } else {
            return "error";
        }
    }
}

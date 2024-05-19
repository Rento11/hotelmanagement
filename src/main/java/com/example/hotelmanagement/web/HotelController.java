package com.example.hotelmanagement.web;

import com.example.hotelmanagement.doa.entities.Hotel;
import com.example.hotelmanagement.service.HotelManager;
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
public class HotelController {
    @Autowired
    private HotelManager hotelManager;

    @GetMapping("hotelsList")
    public String getHotels(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "taille", defaultValue = "6") int taille, @RequestParam(name = "search", defaultValue = "") String keyword) {
        Page<Hotel> hotels = hotelManager.searchHotelsByName(keyword, page, taille);
        model.addAttribute("hotels", hotels.getContent());
        int[] pages = new int[hotels.getTotalPages()];
        for (int i = 0; i < pages.length; i++) {
            pages[i] = i;
        }
        model.addAttribute("pages", pages);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "hotelsList";
    }

    @GetMapping("")
    public String index2(){
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/addHotel")
    public String addHotelGet(Model model) {
        model.addAttribute("hotel", new Hotel());
        return "addHotel";
    }

    @PostMapping("/addHotel")
    public String addHotelPost(@Valid Hotel hotel,
                               @RequestParam(name="name") String name,
                               @RequestParam(name="city") String city,
                               @RequestParam(name="address") String address,
                               @RequestParam(name="imageFileName") MultipartFile imageFileName,
                               BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "addHotel";
        }
        hotel.setName(name);
        hotel.setCity(city);
        hotel.setAddress(address);

        // Handle image file upload
        if (imageFileName != null && !imageFileName.isEmpty()) {
            try {
                // Save the image file
                String fileName = imageFileName.getOriginalFilename();
                String uploadPath = "/static/img";
                // Ensure the directory exists
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }
                // Save the image file
                File uploadedFile = new File(uploadPath + fileName);
                imageFileName.transferTo(uploadedFile);

                hotel.setImageFileName(fileName);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        hotel.setRooms(null);
        hotel.setReview(null);
        hotelManager.addHotel(hotel);
        return "redirect:/hotelsList";
    }

    @GetMapping("/deleteHotel")
    public String deleteHotelAction(@RequestParam(name = "id") Integer id, Integer page, String search) {
        if (hotelManager.deleteHotelById(id)) {
            return "redirect:/hotelsList";
        } else {
            return "error";
        }
    }

    @GetMapping("/updateHotel")
    public String updateHotelGet(Model model, @RequestParam(name = "id") Integer id) {
        Hotel hotel = hotelManager.findHotelById(id);
        if (hotel != null) {
            model.addAttribute("hotel", hotel);
            return "updateHotel";
        } else {
            return "error";
        }
    }
    @PostMapping("/updateHotel")
    public String updateHotelPost(@RequestParam(name="id") Integer id,
                                  @RequestParam(name="name") String name,
                                  @RequestParam(name="city") String city,
                                  @RequestParam(name="address") String address,
                                  @RequestParam(name="imageFileName") MultipartFile imageFileName) {
        Hotel hotel = hotelManager.findHotelById(id);
        if (hotel != null) {
            hotel.setName(name);
            hotel.setCity(city);
            hotel.setAddress(address);

            if (imageFileName != null && !imageFileName.isEmpty()) {
                try {
                    // Save the image file
                    String fileName = "Upload_" + imageFileName.getOriginalFilename();
                    String uploadPath = "/img";
                    // Ensure the directory exists
                    File uploadDir = new File(uploadPath);
                    if (!uploadDir.exists()) {
                        uploadDir.mkdirs();
                    }
                    // Save the image file
                    File uploadedFile = new File(uploadPath + fileName);
                    imageFileName.transferTo(uploadedFile);

                    hotel.setImageFileName(fileName);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }

            hotelManager.updateHotel(hotel);
            return "redirect:/hotelsList";
        } else {
            return "error";
        }
    }
}
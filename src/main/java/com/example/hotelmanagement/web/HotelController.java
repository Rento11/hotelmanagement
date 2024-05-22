package com.example.hotelmanagement.web;

import com.example.hotelmanagement.doa.entities.Hotel;
import com.example.hotelmanagement.service.HotelManager;
import com.example.hotelmanagement.storage.StorageService;
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

import java.io.IOException;

@Controller
public class HotelController {
    @Autowired
    private HotelManager hotelManager;
    @Autowired
    private StorageService storageService;

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

    @GetMapping("index2")
    public String index2() {
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/addHotel")
    public String addHotelGet(Model model) {
        model.addAttribute("hotel", new Hotel());
        return "addHotel";
    }

    @PostMapping("/addHotel")
    public String addHotelPost(@Valid Hotel hotel,
                               @RequestParam(name = "file") MultipartFile file,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addHotel";
        }
        hotel.setImageFileName(file.getOriginalFilename());
        hotelManager.addHotel(hotel);
        storageService.store(file);
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
    public String updateHotelGet(Model model,
                                 @RequestParam(name = "id") Integer id) {
        Hotel hotel = hotelManager.findHotelById(id);
        if (hotel != null) {
            model.addAttribute("hotel", hotel);
            return "updateHotel";
        } else {
            return "error";
        }
    }

    @PostMapping("/updateHotel")
    public String updateHotelPost(@RequestParam(name = "id") Integer id,
                                  @RequestParam(name = "name") String name,
                                  @RequestParam(name = "city") String city,
                                  @RequestParam(name = "address") String address,
                                  @RequestParam(name = "file") MultipartFile file) {
        Hotel hotel = hotelManager.findHotelById(id);
        if (hotel != null) {
            hotel.setName(name);
            hotel.setCity(city);
            hotel.setAddress(address);
            if(file!=null && !file.isEmpty()){
                hotel.setImageFileName(file.getOriginalFilename());
            }else{
                hotel.setImageFileName(hotel.getImageFileName());
            }
            hotelManager.updateHotel(hotel);
            storageService.store(file);
            return "redirect:/hotelsList";
        } else {
            return "error";
        }
    }
}
package com.example.hotelmanagement.web;

import com.example.hotelmanagement.doa.entities.Hotel;
import com.example.hotelmanagement.doa.entities.Room;
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

@Controller
public class HotelController {
    @Autowired
    private HotelManager hotelManager;

    @GetMapping("hotelsList")
    public String getHotels(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "taille", defaultValue = "3") int taille, @RequestParam(name = "search", defaultValue = "") String keyword) {
        Page<Hotel> hotels = hotelManager.searchHotels(keyword, page, taille);
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

    @GetMapping("/addHotel")
    public String addHotelGet(Model model) {
        model.addAttribute("hotel", new Hotel());
        return "addHotel";
    }

    @PostMapping("/addHotel")
    public String addHotelPost(Model model, @Valid Hotel hotel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addHotel";
        }
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
    public String updateHotelPost(Model model, @RequestParam(name = "id") Integer id,Hotel hotel) {
        hotel.setId(id);
        if (hotel != null) {
            hotelManager.updateHotel(hotel);
            return "redirect:/hotelsList";
        } else {
            return "error";
        }
    }
}

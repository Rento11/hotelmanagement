package com.example.hotelmanagement.web;

import com.example.hotelmanagement.doa.entities.Booker;
import com.example.hotelmanagement.service.BookerManager;
import com.example.hotelmanagement.service.BookingManager;
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
public class BookerController {
    @Autowired
    private BookerManager bookerManager;

    @GetMapping("bookersList")
    public String getBookers(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "taille", defaultValue = "3") int taille){
        Page<Booker> bookers = bookerManager.getAllBookers(page, taille);
        model.addAttribute("bookers", bookers.getContent());
        int[] pages = new int[bookers.getTotalPages()];
        for (int i = 0; i < pages.length; i++) {
            pages[i] = i;
        }
        model.addAttribute("pages", pages);
        model.addAttribute("currentPage", page);
        return "bookersList";
    }

    @GetMapping("/addBooker")
    public String addBookerGet(Model model) {
        model.addAttribute("booker", new Booker());
        return "addBooker";
    }

    @PostMapping("/addBooker")
    public String addBookerPost(Model model, BindingResult bindingResult) {
        Booker booker = new Booker(null,null,null);
        if (bindingResult.hasErrors()) {
            return "addBooker";
        }
        bookerManager.addBooker(booker);
        return "redirect:/bookersList";
    }

    @GetMapping("/deleteBooker")
    public String deleteBookerAction(@RequestParam(name = "id") Integer id, Integer page) {
        if (bookerManager.deleteBookerById(id)) {
            return "redirect:/bookersList";
        } else {
            return "error";
        }
    }

    @GetMapping("/updateBooker")
    public String updateBookerGet(Model model, @RequestParam(name = "id") Integer id) {
        Booker booker = bookerManager.findBookerById(id);
        if (booker != null) {
            model.addAttribute("booker", booker);
            return "updateBooker";
        } else {
            return "error";
        }
    }

    @PostMapping("/updateBooker")
    public String updateBookerPost(Model model, @RequestParam(name = "id") Integer id) {
        Booker booker = bookerManager.findBookerById(id);
        if (booker != null) {
            bookerManager.updateBooker(booker);
            return "redirect:/bookersList";
        } else {
            return "error";
        }
    }
}

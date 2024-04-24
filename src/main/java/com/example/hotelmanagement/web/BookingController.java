package com.example.hotelmanagement.web;

import com.example.hotelmanagement.doa.entities.Booking;
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
public class BookingController {
    @Autowired
    private BookingManager bookingManager;

    @GetMapping("bookingsList")
    public String getBookings(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "taille", defaultValue = "3") int taille){
        Page<Booking> bookings = bookingManager.getAllBookings(page, taille);
        model.addAttribute("bookings", bookings.getContent());
        int[] pages = new int[bookings.getTotalPages()];
        for (int i = 0; i < pages.length; i++) {
            pages[i] = i;
        }
        model.addAttribute("pages", pages);
        model.addAttribute("currentPage", page);
        return "bookingsList";
    }

    @GetMapping("/addBooking")
    public String addBookingGet(Model model) {
        model.addAttribute("booking", new Booking());
        return "addBooking";
    }

    @PostMapping("/addBooking")
    public String addBookingPost(Model model, BindingResult bindingResult, @Valid Booking booking) {
        if (bindingResult.hasErrors()) {
            return "addBooking";
        }
        bookingManager.addBooking(booking);
        return "redirect:/bookingsList";
    }

    @GetMapping("/deleteBooking")
    public String deleteBookingAction(@RequestParam(name = "id") Integer id, Integer page) {
        if (bookingManager.deleteBookingById(id)) {
            return "redirect:/bookingsList";
        } else {
            return "error";
        }
    }

    @GetMapping("/updateBooking")
    public String updateBookingGet(Model model, @RequestParam(name = "id") Integer id) {
        Booking booking = bookingManager.findBookingById(id);
        if (booking != null) {
            model.addAttribute("booking", booking);
            return "updateBooking";
        } else {
            return "error";
        }
    }

    @PostMapping("/updateBooking")
    public String updateBookingPost(Model model, @RequestParam(name = "id") Integer id) {
        Booking booking = bookingManager.findBookingById(id);
        if (booking != null) {
            bookingManager.updateBooking(booking);
            return "redirect:/bookingsList";
        } else {
            return "error";
        }
    }
}

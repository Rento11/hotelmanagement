package com.example.hotelmanagement.service;

import com.example.hotelmanagement.doa.entities.Booker;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookerManager {
    public Booker addBooker(Booker booker);
    public boolean deleteBooker(Booker booker);
    public boolean deleteBookerById(Integer id);
    public boolean updateBooker(Booker booker);
    public Booker findBooker(Booker booker);
    public Booker findBookerById(Integer id);
    public List<Booker> getAllBookers();
    public Page<Booker> getAllBookers(int page, int taille);
}

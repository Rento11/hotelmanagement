package com.example.hotelmanagement.service;

import com.example.hotelmanagement.doa.entities.Booker;
import com.example.hotelmanagement.doa.repositories.BookerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookerManagerImplementation implements BookerManager{

    @Autowired
    private BookerRepository bookerRepository;
    @Override
    public Booker addBooker(Booker booker) {
        try{
            return bookerRepository.save(booker);
        }catch(Exception exception){
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @Override
    public boolean deleteBooker(Booker booker) {
        try{
            bookerRepository.delete(booker);
            return true;
        }catch(Exception exception){
            System.out.println(exception.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteBookerById(Integer id) {
        try{
            bookerRepository.delete(bookerRepository.findById(id).get());
            return true;
        }catch(Exception exception){
            System.out.println(exception.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateBooker(Booker booker) {
        try {
            bookerRepository.save(booker);
            return true;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return false;
        }
    }

    @Override
    public Booker findBooker(Booker booker) {
        try{
            return bookerRepository.findById(booker.getId()).get();
        }catch(Exception exception){
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @Override
    public Booker findBookerById(Integer id) {
        try{
            return bookerRepository.findById(id).get();
        }catch(Exception exception){
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @Override
    public List<Booker> getAllBookers() {
        try{
            return bookerRepository.findAll();
        }catch(Exception exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @Override
    public Page<Booker> getAllBookers(int page, int taille) {
        return bookerRepository.findAll(PageRequest.of(page,taille));
    }

}

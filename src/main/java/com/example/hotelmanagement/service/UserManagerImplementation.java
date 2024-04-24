package com.example.hotelmanagement.service;

import com.example.hotelmanagement.doa.entities.User;
import com.example.hotelmanagement.doa.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManagerImplementation implements UserManager{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(User user) {
        try{
            return userRepository.save(user);
        }catch(Exception exception){
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @Override
    public boolean deleteUser(User user) {
        try{
            userRepository.delete(user);
            return true;
        }catch(Exception exception){
            System.out.println(exception.getMessage());
            return false;
        }    }

    @Override
    public boolean deleteUserById(Integer id) {
        try{
            userRepository.delete(userRepository.findById(id).get());
            return true;
        }catch(Exception exception){
            System.out.println(exception.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateUser(User user) {
        try{
            userRepository.save(user);
            return true;
        }catch(Exception exception){
            System.out.println(exception.getMessage());
            return false;
        }
    }

    @Override
    public User findUser(User user) {
        try{
            return userRepository.findById(user.getId()).get();
        }catch(Exception exception){
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @Override
    public User findUserById(Integer id) {
        try{
            return userRepository.findById(id).get();
        }catch(Exception exception){
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        try{
            return userRepository.findAll();
        }catch(Exception exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @Override
    public Page<User> getAllUsers(int page, int taille) {
        return userRepository.findAll(PageRequest.of(page,taille));
    }

    @Override
    public Page<User> searchUsers(String keyword, int page, int taille) {
        //return userRepository.findUserByUserNumberContainingIgnoreCase(keyword, PageRequest.of(page, taille));
        return null;
    }
}

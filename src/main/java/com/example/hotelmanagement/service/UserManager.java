package com.example.hotelmanagement.service;

import com.example.hotelmanagement.doa.entities.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserManager {
    public User addUser(User user);
    public boolean deleteUser(User user);
    public boolean deleteUserById(Integer id);
    public boolean updateUser(User user);
    public User findUser(User user);
    public User findUserById(Integer id);
    public List<User> getAllUsers();
    public Page<User> getAllUsers(int page, int taille);
    public Page<User> searchUsers(String keyword, int page, int taille);
}

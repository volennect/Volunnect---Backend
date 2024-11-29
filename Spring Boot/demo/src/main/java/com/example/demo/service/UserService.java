package com.example.demo.service;

import com.example.demo.entity.Users;

import java.util.List;

public interface UserService {
    String save(Users users);

    Iterable<Users> listAll(Users users);

    Users getUserById(String userId);

    List<String> getUserInterests(String userId);

    List<Users> getAllUsers();
}

package com.example.demo.service;

import com.example.demo.entity.Users;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Override
    public String save(Users users) {
        return userRepo.save(users).getUserId();
    }

    @Override
    public List<Users> listAll(Users users){
        return userRepo.findAll();
    }

    @Override
    public Users getUserById(String userId){
        return userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public List<String> getUserInterests(String userId){
        Users user = userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        List<String> interests = user.getIntrests();
        return interests;
    }

    @Override
    public List<Users> getAllUsers() {
        return userRepo.findAll();
    }


}

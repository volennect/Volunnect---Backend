package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repo.SignUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {

    @Autowired
    private SignUpRepository signUpRepository;

    public User saveUser(User user) {
        return signUpRepository.save(user);
    }
}

package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repo.SignUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin("http://localhost:5173/")
@RestController
@RequestMapping("/api/users")
public class SignUpController {

    @Autowired
    private SignUpRepository signUpRepository;

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User savedUser = signUpRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }



    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        Optional<User> user = signUpRepository.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

package com.example.demo.controller;


import com.example.demo.entity.Events;
import com.example.demo.entity.Users;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repo.UserRepo;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@RestController
@RequestMapping("/users")
public class filterEventForUserController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private UserService userService;

    @PostMapping
    public String saveUser(@RequestBody Users users){
        return userService.save(users);
    }

    @PutMapping("/{userId}")
    Users updateUser(@RequestBody Users newUser,@PathVariable(name="id") String userID){
        return userRepo.findById(userID)
                .map(users -> {
                    users.setName(newUser.getName());
                    users.setAge(newUser.getAge());
                    users.setIntrests(newUser.getIntrests());
                    return userRepo.save(users);
                }).orElseThrow(() -> new UserNotFoundException("User not found with given ID "+userID));
    }

    @GetMapping
    public Iterable<Users> getusers(Users users){
        return userService.listAll(users);
    }

    @GetMapping("/{userId}")
    public Users getUserById(@PathVariable String userId){
        return userService.getUserById(userId);
    }

    @GetMapping("/filterEvents/{id}")
    public List<Events> filterEventsByUserInterests(@PathVariable("id") String userId) {

        List<String> intrests = userService.getUserInterests(userId);

        Query query = new Query();
        Criteria[] criteria = new Criteria[intrests.size()];

        for (int i = 0; i < intrests.size(); i++) {
            criteria[i] = Criteria.where("EventType").regex(intrests.get(i), "i");
        }

        query.addCriteria(new Criteria().orOperator(criteria));
        return mongoTemplate.find(query, Events.class);
    }
}

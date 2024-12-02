package com.example.demo.controller;

import com.example.demo.entity.FilterEvents;
import com.example.demo.entity.FilterUsers;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repo.filterUserRepo;
import com.example.demo.service.filterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/users")
public class filterEventForUserController {

    @Autowired
    private filterUserRepo filterUserRepo;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private filterUserService userService;

    @PostMapping
    public String saveUser(@RequestBody FilterUsers filterUsers){
        return userService.save(filterUsers);
    }

    @PutMapping("/{userId}")
    FilterUsers updateUser(@RequestBody FilterUsers newUser, @PathVariable(name="id") String userID){
        return filterUserRepo.findById(userID)
                .map(users -> {
                    users.setName(newUser.getName());
                    users.setAge(newUser.getAge());
                    users.setIntrests(newUser.getIntrests());
                    users.setUnavailableDates(newUser.getUnavailableDates());
                    return filterUserRepo.save(users);
                }).orElseThrow(() -> new UserNotFoundException("User not found with given ID "+userID));
    }

    @GetMapping
    public Iterable<FilterUsers> getusers(FilterUsers filterUsers){
        return userService.listAll(filterUsers);
    }

    @GetMapping("/{userId}")
    public FilterUsers getUserById(@PathVariable String userId){
        return userService.getUserById(userId);
    }

    @GetMapping("/filterEvents/{id}")
    public List<FilterEvents> filterEventsByUserInterestsAndAvailability(@PathVariable("id") String userId) {

        List<String> interests = userService.getUserInterests(userId);
        List<LocalDate> unavailableDates = userService.getUnavailableDates(userId);

        if (interests == null || interests.isEmpty()) {
            return mongoTemplate.findAll(FilterEvents.class);
        }

        Query query = new Query();

        Criteria combinedCriteria = new Criteria().andOperator(
                new Criteria().orOperator(
                        interests.stream()
                                .map(interest -> Criteria.where("EventType").regex(interest, "i"))
                                .toArray(Criteria[]::new)
                ),
                new Criteria().norOperator(
                        unavailableDates.stream()
                                .map(date -> new Criteria().andOperator(
                                        Criteria.where("eventStartDate").lte(date),
                                        Criteria.where("eventEndDate").gte(date)
                                ))
                                .toArray(Criteria[]::new)
                )
        );

        query.addCriteria(combinedCriteria);

        return mongoTemplate.find(query, FilterEvents.class);

    }
}


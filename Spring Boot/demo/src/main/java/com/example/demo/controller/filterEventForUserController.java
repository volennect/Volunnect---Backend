package com.example.demo.controller;

import com.example.demo.entity.FilterEvents;
import com.example.demo.entity.FilterUsers;
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
@RequestMapping("/filterEvents")
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

    @GetMapping
    public Iterable<FilterUsers> getusers(FilterUsers filterUsers){
        return userService.listAll(filterUsers);
    }


    //Filtering Events For the Volunteers based on their interested event types and availability of the volunteers is DONE.
    // "PLZ NEVER EDIT THIS CODE AGAIN BECAUSE AFTER MILLIONS OF ATTEMPTS FINALLY ITS WORKING NOW."
    @GetMapping("/{userId}")
    public List<FilterEvents> filterEventsByUserInterestsAndAvailability(@PathVariable("userId") String userId) {

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


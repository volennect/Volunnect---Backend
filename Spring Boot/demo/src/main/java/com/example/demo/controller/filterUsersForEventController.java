package com.example.demo.controller;

import com.example.demo.entity.Events;
import com.example.demo.entity.Users;
import com.example.demo.repo.EventRepo;
import com.example.demo.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/events")
public class filterUsersForEventController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private EventService eventService;

    @Autowired
    private EventRepo eventRepo;

    @PostMapping("/save")
    public String saveEvent(@RequestBody Events events){
        return eventService.save(events);
    }

    @GetMapping("/getall")
    public Iterable<Events> getEvents(Events events){
        return eventService.listAll(events);
    }

    @GetMapping("/filterUsers/{EventId}")
    public List<Users> filterUsersByEventType(@PathVariable("EventId") String EventId){

        String eventType = eventService.getEventType(EventId);

        Query query = new Query();
        query.addCriteria(Criteria.where("intrests").regex("^" + eventType + "$", "i"));

        return mongoTemplate.find(query, Users.class);
    }

}

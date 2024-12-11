package com.example.demo.controller;

import com.example.demo.entity.Event;
import com.example.demo.entity.Volunteer;
import com.example.demo.service.EventService;
import com.example.demo.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/save")
public class postController {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private VolunteerService volunteerService;

    @Autowired
    private EventService EventService;

    @PostMapping("/user")
    public Volunteer saveUser(@RequestBody Volunteer volunteer){
        return volunteerService.createVolunteer(volunteer);
    }

    @PostMapping("event")
    public Event saveEvent(@RequestBody Event event){
        return EventService.saveOrUpdate(event);
    }
}

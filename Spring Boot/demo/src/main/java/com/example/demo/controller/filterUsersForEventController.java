package com.example.demo.controller;

import com.example.demo.entity.FilterEvents;
import com.example.demo.entity.FilterUsers;
import com.example.demo.repo.FilterEventRepo;
import com.example.demo.service.filterEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class filterUsersForEventController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private filterEventService filterEventService;

    @Autowired
    private FilterEventRepo filterEventRepo;

    @PostMapping("/save")
    public String saveEvent(@RequestBody FilterEvents filterEvents){
        return filterEventService.save(filterEvents);
    }

    @GetMapping("/getall")
    public Iterable<FilterEvents> getEvents(FilterEvents filterEvents){
        return filterEventService.listAll(filterEvents);
    }

    @GetMapping("/filterUsers/{EventId}")
    public List<FilterUsers> filterUsersByEventType(@PathVariable("EventId") String EventId){

        String eventType = filterEventService.getEventType(EventId);

        Query query = new Query();
        query.addCriteria(Criteria.where("interests").regex("^" + eventType + "$", "i"));

        return mongoTemplate.find(query, FilterUsers.class);
    }

}

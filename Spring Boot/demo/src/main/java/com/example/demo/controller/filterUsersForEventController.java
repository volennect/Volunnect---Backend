package com.example.demo.controller;

import com.example.demo.entity.FilterEvents;
import com.example.demo.entity.FilterUsers;
import com.example.demo.service.filterEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/filterVolunteers")
@CrossOrigin(origins = "*")
public class filterUsersForEventController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private filterEventService filterEventService;

    @PostMapping
    public Long saveEvent(@RequestBody FilterEvents filterEvents){
        return filterEventService.save(filterEvents);
    }

    //Filter users with the highest rated order by ratings that match user interests with the event type.
    // "PLZ NEVER EDIT THIS CODE AGAIN BECAUSE AFTER MILLIONS OF ATTEMPTS FINALLY ITS WORKING NOW."
    @GetMapping("/{eventId}")
    public List<FilterUsers> filterTopRatedOrdersByUserInterestsAndEventType(@PathVariable("eventId") Long eventId) {
        String eventType = filterEventService.getEventType(eventId);
        LocalDate eventStartDate = filterEventService.getEventStartDate(eventId);
        LocalDate eventEndDate = filterEventService.getEventEndDate(eventId);

        Query query = new Query();
        query.addCriteria(Criteria.where("interests").regex("^" + eventType + "$", "i"));
        query.addCriteria(new Criteria().norOperator(
                new Criteria().andOperator(
                        Criteria.where("unavailableDates").lte(eventEndDate),
                        Criteria.where("unavailableDates").gte(eventStartDate)
                )
        ));
        query.with(Sort.by(Sort.Direction.DESC, "ratings"));
        return mongoTemplate.find(query, FilterUsers.class);
    }

}

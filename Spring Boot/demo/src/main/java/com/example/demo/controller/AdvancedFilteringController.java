package com.example.demo.controller;

import com.example.demo.entity.Event;
import com.example.demo.entity.Volunteer;
import com.example.demo.service.EventService;
import com.example.demo.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/filter")
@CrossOrigin(origins = "*")
public class AdvancedFilteringController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private VolunteerService volunteerService;

    @Autowired
    private EventService EventService;

    //Filtering Events For the Volunteers based on their interested event types and availability of the volunteers is DONE.
    // "PLZ NEVER EDIT THIS CODE AGAIN BECAUSE AFTER MILLIONS OF ATTEMPTS FINALLY ITS WORKING NOW."
    @GetMapping("/events/{userId}")
    public List<Event> filterEventsByUserInterestsAndAvailability(
            @PathVariable("userId") String userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        List<String> interests = volunteerService.getVolunteerInterests(userId);
        List<LocalDate> unavailableDates = volunteerService.getUnavailableDates(userId);

        if (interests == null || interests.isEmpty()) {
            return mongoTemplate.findAll(Event.class);
        }

        Query query = new Query();
        Criteria interestsCriteria = new Criteria().orOperator(
                interests.stream()
                        .map(interest -> Criteria.where("type").regex(interest, "i"))
                        .toArray(Criteria[]::new)
        );
        Criteria availabilityCriteria = new Criteria().andOperator(
                unavailableDates.stream()
                        .map(unavailableDate -> Criteria.where("startDate").lte(unavailableDate).and("endDate").gte(unavailableDate))
                        .map(overlapCriteria -> new Criteria().norOperator(overlapCriteria))
                        .toArray(Criteria[]::new)
        );

        Criteria combinedCriteria = new Criteria().andOperator(interestsCriteria, availabilityCriteria);
        query.addCriteria(combinedCriteria);
        query.skip(page * size).limit(size);
        return mongoTemplate.find(query, Event.class);
    }


    //Filter users with the highest rated order by ratings that match user interests with the event type.
    // "PLZ NEVER EDIT THIS CODE AGAIN BECAUSE AFTER MILLIONS OF ATTEMPTS FINALLY ITS WORKING NOW."
    @GetMapping("/volunteers/{eventId}")
    public List<Volunteer> filterTopRatedVolunteersByTheirInterestsThatmatchWithEventType(
            @PathVariable("eventId") String eventId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        String eventType = EventService.getEventType(eventId);
        LocalDate eventStartDate = EventService.getEventStartDate(eventId);
        LocalDate eventEndDate = EventService.getEventEndDate(eventId);

        Query query = new Query();
        query.addCriteria(Criteria.where("interests").regex("^" + eventType + "$", "i"));
        query.addCriteria(new Criteria().norOperator(
                new Criteria().andOperator(
                        Criteria.where("unavailableDates").lte(eventEndDate),
                        Criteria.where("unavailableDates").gte(eventStartDate)
                )
        ));
        query.with(Sort.by(Sort.Direction.DESC, "ratings"));
        query.skip(page * size).limit(size);
        return mongoTemplate.find(query, Volunteer.class);
    }
}

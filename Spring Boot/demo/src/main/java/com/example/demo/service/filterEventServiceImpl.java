package com.example.demo.service;

import com.example.demo.entity.FilterEvents;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repo.FilterEventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class filterEventServiceImpl implements filterEventService {

    @Autowired
    private FilterEventRepo filterEventRepo;

    @Override
    public Long save(FilterEvents filterEvents) {
        return filterEventRepo.save(filterEvents).getEventId();
    }


    @Override
    public FilterEvents getEventById(Long eventId) {
        return filterEventRepo.findById(eventId).orElseThrow(() -> new UserNotFoundException("Event not found"));
    }

    @Override
    public String getEventType(Long eventId) {
        FilterEvents event = getEventById(eventId);
        return event.getEventType();
    }

    @Override
    public LocalDate getEventStartDate(Long eventId){
        Optional<FilterEvents> eventStartDate = filterEventRepo.findById(eventId);
        return eventStartDate.get().getEventStartDate();
    }

    @Override
    public LocalDate getEventEndDate(Long eventId){
        Optional<FilterEvents> eventEndDate = filterEventRepo.findById(eventId);
        return eventEndDate.get().getEventEndDate();
    }
}


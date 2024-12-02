package com.example.demo.service;

import com.example.demo.entity.FilterEvents;
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
    public String save(FilterEvents filterEvents) {
        return filterEventRepo.save(filterEvents).getEventId();
    }

    @Override
    public Iterable<FilterEvents> listAll(FilterEvents filterEvents) {
        return filterEventRepo.findAll();
    }

    @Override
    public String getEventType(String EventId) {
        Optional<FilterEvents> event = filterEventRepo.findById(EventId);
        if (event.isEmpty()) {
            throw new IllegalArgumentException("Event not found for ID: " + EventId);
        }
        return event.get().getEventType();
    }

    @Override
    public LocalDate getEventStartDate(String EventId){
        Optional<FilterEvents> eventStartDate = filterEventRepo.findById(EventId);
        return eventStartDate.get().getEventStartDate();
    }

    @Override
    public LocalDate getEventEndDate(String EventId){
        Optional<FilterEvents> eventEndDate = filterEventRepo.findById(EventId);
        return eventEndDate.get().getEventEndDate();
    }
}


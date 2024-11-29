package com.example.demo.service;

import com.example.demo.entity.Events;
import com.example.demo.repo.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepo eventRepo;
    private EventService eventService;

    @Override
    public String save(Events events){
        return eventRepo.save(events).getEventId();
    }

    @Override
    public Iterable<Events> listAll(Events events) {
        return eventRepo.findAll();
    }

    public String getEventType(String EventId) {
        Optional<Events> event = eventRepo.findById(EventId);
        if (event.isEmpty()) {
            throw new IllegalArgumentException("Event not found for ID: " + EventId);
        }
        return event.get().getEventType();
    }


}

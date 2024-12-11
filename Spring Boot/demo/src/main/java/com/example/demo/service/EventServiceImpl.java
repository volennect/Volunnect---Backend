package com.example.demo.service;

import com.example.demo.entity.Event;
import com.example.demo.repo.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService{

    @Autowired
    private EventRepository repository;

    @Override
    public Event saveOrUpdate(Event event) {
        repository.save(event);
        return event;
    }

    @Override
    public Iterable<Event> listAll() {
        return repository.findAll();
    }

    @Override
    public String deleteEvent(String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return id;
        } else {
            throw new IllegalArgumentException("Event with ID " + id + " does not exist.");
        }
    }

    @Override
    public Event getEventById(String eventId) {
        Optional<Event> event = repository.findById(eventId);
        if (event.isPresent()) {
            return event.get();
        } else {
            throw new IllegalArgumentException("Event with ID " + eventId + " not found.");
        }
    }

    //for Advanced filtering
    @Override
    public String getEventType(String id){
        Event event = getEventById(id);
        return event.getType();
    }

    @Override
    public LocalDate getEventStartDate(String id){
        Event event = getEventById(id);
        return event.getStartDate();
    }

    @Override
    public LocalDate getEventEndDate(String id){
        Event event = getEventById(id);
        return event.getEndDate();
    }

}

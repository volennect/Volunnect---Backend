package com.EventScheduling.Volunnect.Service;

import com.EventScheduling.Volunnect.Entity.Event;
import com.EventScheduling.Volunnect.Repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    // Save or update an event
    public void saveOrUpdate(Event event) {
        repository.save(event);
    }

    // List all events
    public Iterable<Event> listAll() {
        return repository.findAll();
    }

    // Delete an event by ID
    public void deleteEvent(String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Event with ID " + id + " does not exist.");
        }
    }

    // Get an event by ID
    public Event getEventById(String eventId) {
        Optional<Event> event = repository.findById(eventId);
        if (event.isPresent()) {
            return event.get();
        } else {
            throw new IllegalArgumentException("Event with ID " + eventId + " not found.");
        }
    }
}

package com.example.demo.example.demo.controller;

import com.example.demo.example.demo.entity.Event;
import com.example.demo.example.demo.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("api/v1/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping(value="/save")
    public String saveEvent(@RequestBody Event events) {
        eventService.saveOrUpdate(events);
        return events.getId();
    }

    @GetMapping(value="/getall")
    public Iterable<Event> getEvents() {
        return eventService.listAll();
    }

    @PutMapping(value="/edit/{id}")
    public Event update(@RequestBody Event event, @PathVariable(name = "id") String id){
        event.setId(id);
        eventService.saveOrUpdate(event);
        return event;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEvent(@PathVariable("id") String id){
        eventService.deleteEvent(id);
    }

    @RequestMapping("/{id}")
    public Event getEvent(@PathVariable(name = "id") String eventid){
        return eventService.getEventById(eventid);
    }
}

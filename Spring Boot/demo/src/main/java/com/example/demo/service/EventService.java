package com.example.demo.service;

import com.example.demo.entity.Event;

import java.time.LocalDate;

public interface EventService {

    Event saveOrUpdate(Event event);

    Iterable<Event> listAll();

    String deleteEvent(String id);

    Event getEventById(String eventId);

    String getEventType(String id);

    LocalDate getEventStartDate(String id);

    LocalDate getEventEndDate(String id);
}

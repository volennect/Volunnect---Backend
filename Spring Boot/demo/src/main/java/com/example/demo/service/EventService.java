package com.example.demo.service;

import com.example.demo.entity.Events;

public interface EventService {

    String save(Events events);

    Iterable<Events> listAll(Events events);

    String getEventType(String EventId);
}

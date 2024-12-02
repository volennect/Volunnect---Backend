package com.example.demo.service;

import com.example.demo.entity.FilterEvents;

import java.time.LocalDate;

public interface filterEventService {

    String save(FilterEvents filterEvents);

    Iterable<FilterEvents> listAll(FilterEvents filterEvents);

    String getEventType(String EventId);

    LocalDate getEventStartDate(String EventId);

    LocalDate getEventEndDate(String EventId);
}


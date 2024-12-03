package com.example.demo.service;

import com.example.demo.entity.FilterEvents;

import java.time.LocalDate;

public interface filterEventService {

    Long save(FilterEvents filterEvents);

    FilterEvents getEventById(Long EventId);

    String getEventType(Long EventId);

    LocalDate getEventStartDate(Long EventId);

    LocalDate getEventEndDate(Long EventId);
}


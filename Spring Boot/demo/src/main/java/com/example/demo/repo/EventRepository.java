package com.example.demo.repo;

import com.example.demo.entity.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EventRepository extends MongoRepository <Event, String> {
    Optional<Event> findById(String EventId);
}

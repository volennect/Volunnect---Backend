package com.example.demo.example.demo.repo;

import com.example.demo.example.demo.entity.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository <Event, String> {
}

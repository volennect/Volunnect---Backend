package com.example.demo.repo;

import com.example.demo.entity.FilterEvents;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FilterEventRepo extends MongoRepository<FilterEvents, String> {
    Optional<FilterEvents> findById(String EventId);
}


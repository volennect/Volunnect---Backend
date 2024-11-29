package com.example.demo.repo;

import com.example.demo.entity.Events;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventRepo extends MongoRepository<Events,String> {
    Optional<Events> findById(String EventId);
}

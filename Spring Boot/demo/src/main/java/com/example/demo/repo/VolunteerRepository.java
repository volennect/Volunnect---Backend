package com.example.demo.repo;

import com.example.demo.entity.Volunteer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VolunteerRepository extends MongoRepository<Volunteer, String> {
    boolean existsByEmail(String email);
}

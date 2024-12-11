package com.example.demo.repo;

import com.example.demo.entity.Volunteer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolunteerRepository extends MongoRepository<Volunteer, String> {
}

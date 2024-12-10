package com.example.demo.repo;

import com.example.demo.entity.Application;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ApplicationRepository extends MongoRepository<Application, String> {
    List<Application> findByVolunteerId(String volunteerId);
    List<Application> findByOrganizationIdAndEventId(String organizationId, String eventId);
}


package com.ApplicationManagement.ApplicationManagement.repo;

import com.ApplicationManagement.ApplicationManagement.entity.Volunteer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolunteerRepository extends MongoRepository<Volunteer, String> {
}

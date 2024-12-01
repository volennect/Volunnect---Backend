package com.ApplicationManagement.ApplicationManagement.repo;

import com.ApplicationManagement.ApplicationManagement.entity.Application;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends MongoRepository<Application, String> {
}

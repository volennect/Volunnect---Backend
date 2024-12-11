package com.example.demo.repo;

import com.example.demo.entity.FilterUsers;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface filterUserRepo extends MongoRepository<FilterUsers, Long> {
}

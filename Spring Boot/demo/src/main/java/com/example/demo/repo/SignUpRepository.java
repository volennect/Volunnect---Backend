package com.example.demo.repo;

import com.example.demo.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SignUpRepository extends MongoRepository<User, String> {
}

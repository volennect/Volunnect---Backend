package com.example.chatapp.cht_Repository;

import com.example.chatapp.cht_Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByName(String name); // Custom query to find user by name
}

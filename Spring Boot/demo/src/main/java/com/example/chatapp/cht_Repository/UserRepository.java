package com.example.chatapp.cht_Repository;

import com.example.chatapp.cht_Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByName(String name); // This allows us to fetch users by name
}

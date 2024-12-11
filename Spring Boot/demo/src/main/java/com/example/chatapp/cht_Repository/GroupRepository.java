package com.example.chatapp.cht_Repository;

import com.example.chatapp.cht_Entity.Group;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GroupRepository extends MongoRepository<Group, String> {
}

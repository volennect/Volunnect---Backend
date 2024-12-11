package com.example.chatapp.cht_Service;

import com.example.chatapp.cht_Entity.User;
import com.example.chatapp.cht_Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(String name) {
        User user = new User();
        user.setName(name);
        return userRepository.save(user);
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    // Add this method to fetch all users
    public List<User> getAllUsers() {
        return userRepository.findAll(); // This assumes `UserRepository` extends MongoRepository or JpaRepository
    }
}

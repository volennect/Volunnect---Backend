package com.example.demo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.demo.entity.User;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    // Check if a user exists by email
    boolean existsByEmail(String email);

    // Find a user by email
    User findByEmail(String email);

    // Find a user by reset password token
    User findByResetPasswordToken(String resetPasswordToken);  // Method to find a user by reset password token

    // Find a user by email and reset password token
    Optional<User> findByEmailAndResetPasswordToken(String email, String resetPasswordToken);

    // Optional: Find a user by email and password (if needed for login authentication)
    Optional<User> findByEmailAndPassword(String email, String password);  // Method to find a user by email and password (if needed)

}

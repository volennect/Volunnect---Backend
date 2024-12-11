package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repo.UserRepository;
import com.example.demo.service.EmailService; // Email service for sending emails
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService; // Injecting email service

    private static final int MIN_PASSWORD_LENGTH = 6;

    // Helper method to validate password strength
    private boolean isPasswordStrong(String password) {
        return password != null && password.length() >= MIN_PASSWORD_LENGTH;
    }

    // POST Mapping - Log In User
    // POST Mapping - Create new user (Sign Up)
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("This email is already registered. Please log in.");
        }
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            return ResponseEntity.badRequest().body("Password and Confirm Password do not match.");
        }
        if (!isPasswordStrong(user.getPassword())) {
            return ResponseEntity.badRequest().body("Password must be at least " + MIN_PASSWORD_LENGTH + " characters long.");
        }
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    // POST Mapping - Log In User
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
        User existingUser = userRepository.findByEmail(loginRequest.getEmail());
        if (existingUser == null) {
            return ResponseEntity.badRequest().body("This email does not exist.");
        }
        if (!existingUser.getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.badRequest().body("Your password is incorrect.");
        }
        return ResponseEntity.ok("Login successful!");
    }

    // POST Mapping - Request password reset (Forgot Password)
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody User resetRequest) {
        User existingUser = userRepository.findByEmail(resetRequest.getEmail());
        if (existingUser == null) {
            return ResponseEntity.badRequest().body("This email does not exist.");
        }

        // Generate reset password token and expiration time
        String token = UUID.randomUUID().toString();
        existingUser.setResetPasswordToken(token);
        existingUser.setResetPasswordTokenExpiration(LocalDateTime.now().plusHours(1)); // Token expires in 1 hour
        userRepository.save(existingUser);

        // Prepare the reset password link
        String resetLink = "http://localhost:5173/update-password?token=" + token;

        // Prepare the email content with only the reset link
        String emailBody = "Hi Dr Sir/Madam,\n\n" +
                "Please verify this email is yours to reset your password.\n\n" +
                "Click the link below to reset your password:\n\n" +
                resetLink + "\n\n" +
                "If you did not request this, please ignore this email.";

        // Send the email
        emailService.sendEmail(existingUser.getEmail(), "Reset Your Password", emailBody);

        return ResponseEntity.ok("Password reset email sent. Please check your inbox.");
    }

    // POST Mapping - Reset Password (Forgot Password verification and update)
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam String token, @RequestBody User resetRequest) {
        User existingUser = userRepository.findByResetPasswordToken(token);
        if (existingUser == null) {
            return ResponseEntity.badRequest().body("Invalid or expired token.");
        }

        // Check if the token has expired
        if (existingUser.getResetPasswordTokenExpiration().isBefore(LocalDateTime.now())) {
            return ResponseEntity.badRequest().body("Token has expired.");
        }

        // Check if password and confirm password match
        if (!resetRequest.getPassword().equals(resetRequest.getConfirmPassword())) {
            return ResponseEntity.badRequest().body("Password and Confirm Password do not match.");
        }

        // Validate password strength
        if (!isPasswordStrong(resetRequest.getPassword())) {
            return ResponseEntity.badRequest().body("Password must be at least " + MIN_PASSWORD_LENGTH + " characters long.");
        }

        // Reset the password and clear the reset token
        existingUser.setPassword(resetRequest.getPassword());
        existingUser.setConfirmPassword(resetRequest.getConfirmPassword()); // Optional: confirmPassword might not need to be stored
        existingUser.setResetPasswordToken(null); // Clear the reset token after successful reset
        existingUser.setResetPasswordTokenExpiration(null); // Clear expiration
        userRepository.save(existingUser);

        return ResponseEntity.ok("Password reset successfully!");
    }

    // GET Mapping - Retrieve user by email
    @GetMapping("/{email}")
    public ResponseEntity<?> getUser(@PathVariable String email) {
        return userRepository.findById(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT Mapping - Update user details
    @PutMapping("/{email}")
    public ResponseEntity<?> updateUser(@PathVariable String email, @RequestBody User updatedUser) {
        return userRepository.findById(email).map(user -> {
            if (!updatedUser.getPassword().equals(updatedUser.getConfirmPassword())) {
                return ResponseEntity.badRequest().body("Password and Confirm Password do not match.");
            }
            user.setName(updatedUser.getName());
            user.setPassword(updatedUser.getPassword());
            user.setConfirmPassword(updatedUser.getConfirmPassword());
            userRepository.save(user);
            return ResponseEntity.ok("User updated successfully!");
        }).orElse(ResponseEntity.notFound().build());
    }

    // PUT Mapping - Update Password (After token validation)
    @PutMapping("/update-password")
    public ResponseEntity<?> updatePassword(@RequestParam String token, @RequestBody User updatedUser) {
        // Find the user by the reset password token
        User existingUser = userRepository.findByResetPasswordToken(token);

        // If the user does not exist with the given token, return an error
        if (existingUser == null) {
            return ResponseEntity.badRequest().body("Invalid or expired token.");
        }

        // Check if the token has expired
        if (existingUser.getResetPasswordTokenExpiration().isBefore(LocalDateTime.now())) {
            return ResponseEntity.badRequest().body("Token has expired.");
        }

        // Check if the password and confirm password match
        if (!updatedUser.getPassword().equals(updatedUser.getConfirmPassword())) {
            return ResponseEntity.badRequest().body("Password and Confirm Password do not match.");
        }

        // Update the password
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setConfirmPassword(updatedUser.getConfirmPassword());  // Optional: confirmPassword might not need to be stored
        existingUser.setResetPasswordToken(null);  // Clear the reset token after successful reset
        existingUser.setResetPasswordTokenExpiration(null); // Clear expiration time
        userRepository.save(existingUser);  // Save the updated user in the database

        return ResponseEntity.ok("Password updated successfully!");
    }

    // DELETE Mapping - Delete user
    @DeleteMapping("/{email}")
    public ResponseEntity<?> deleteUser(@PathVariable String email) {
        if (userRepository.existsById(email)) {
            userRepository.deleteById(email);
            return ResponseEntity.ok("User deleted successfully!");
        }
        return ResponseEntity.notFound().build();
    }
}

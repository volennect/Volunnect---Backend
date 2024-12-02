package com.example.chatapp.cht_Controller;

import com.example.chatapp.cht_DTO.MessageDTO;
import com.example.chatapp.cht_Entity.User;
import com.example.chatapp.cht_Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private UserService userService;

    // Endpoint to create a new user
    @PostMapping("/users")
    public User createUser(@RequestBody String name) {
        return userService.createUser(name);
    }

    // Endpoint to send a message between users
    @PostMapping("/send-message")
    public String sendMessage(@RequestBody MessageDTO messageDTO) {
        // Here, you can integrate the logic to send messages to specific users via WebSocket
        // For simplicity, let's return a success message for now.
        return "Message sent from " + messageDTO.getFromUserId() + " to " + messageDTO.getToUserId();
    }
}

package com.example.chatapp.cht_Controller;

import com.example.chatapp.cht_DTO.MessageDTO;
import com.example.chatapp.cht_Entity.Message;
import com.example.chatapp.cht_Entity.User;
import com.example.chatapp.cht_Service.ChatService;
import com.example.chatapp.cht_Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
@CrossOrigin(origins="http://localhost:3000/")
public class ChatController {

    @Autowired
    private UserService userService;

    @Autowired
    private ChatService chatService;

    @PostMapping("/users")
    public User createUser(@RequestBody String name) {
        return userService.createUser(name);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/send-message")
    public String sendMessage(@RequestBody MessageDTO messageDTO) {
        chatService.saveMessage(messageDTO);
        return "Message sent successfully!";
    }

    @GetMapping("/messages/{groupId}")
    public List<Message> getMessagesByGroupId(@PathVariable String groupId) {
        return chatService.getMessagesByGroupId(groupId);
    }

    @GetMapping("/messages/{fromUserId}/{toUserId}")
    public List<Message> getMessages(@PathVariable String fromUserId, @PathVariable String toUserId) {
        return chatService.getMessagesByUserIds(fromUserId, toUserId);
    }

    // New endpoint to fetch all messages sent by a specific user (sender)
    @GetMapping("/messages/sender/{senderId}")
    public List<Message> getMessagesBySenderId(@PathVariable String senderId) {
        return chatService.getMessagesBySenderId(senderId);
    }
}

package com.example.chatapp.cht_Service;

import com.example.chatapp.cht_DTO.MessageDTO;
import com.example.chatapp.cht_Entity.Message;
import com.example.chatapp.cht_Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    @Autowired
    private MessageRepository messageRepository;

    public void saveMessage(MessageDTO messageDTO) {
        // Get groupId from messageDTO
        String groupId = messageDTO.getGroupId();  // Ensure the method is available

        // Creating a new message entity with the groupId
        Message message = new Message(
                groupId,                          // groupId should be passed here
                messageDTO.getFromUserId(),        // Sender's ID
                messageDTO.getToUserId(),          // Receiver's ID
                messageDTO.getMessageContent(),    // Message content
                messageDTO.isReadStatus(),        // Read status
                messageDTO.getPriority()           // Priority
        );
        // Save the message to MongoDB
        messageRepository.save(message);
    }

    public List<Message> getMessagesByGroupId(String groupId) {
        // Fetch messages for a particular group ID
        return messageRepository.findByGroupId(groupId);
    }

    // Get messages by sender and receiver's IDs
    public List<Message> getMessagesByUserIds(String fromUserId, String toUserId) {
        return messageRepository.findBySenderIdAndReceiverId(fromUserId, toUserId);
    }

    // Get messages by a particular sender (for front-end usage)
    public List<Message> getMessagesBySenderId(String senderId) {
        return messageRepository.findBySenderId(senderId);
    }
}

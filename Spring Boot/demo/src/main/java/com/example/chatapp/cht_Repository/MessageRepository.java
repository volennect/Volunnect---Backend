package com.example.chatapp.cht_Repository;

import com.example.chatapp.cht_Entity.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessageRepository extends MongoRepository<Message, String> {

    // Find messages by groupId
    List<Message> findByGroupId(String groupId);

    // Find messages by sender and receiver IDs
    List<Message> findBySenderIdAndReceiverId(String senderId, String receiverId);

    // Find messages by sender ID
    List<Message> findBySenderId(String senderId);
}

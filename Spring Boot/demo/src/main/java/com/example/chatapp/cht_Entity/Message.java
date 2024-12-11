package com.example.chatapp.cht_Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "messages")
public class Message {

    @Id
    private String id;
    private String groupId;
    private String senderId; // Sender's ID
    private String receiverId; // Receiver's ID
    private String messageContent;
    private Date timestamp;
    private boolean readStatus;
    private int priority = 1;

    // Constructor updated to include senderId and receiverId
    public Message(String groupId, String senderId, String receiverId, String messageContent, boolean readStatus, int priority) {
        this.groupId = groupId;
        this.senderId = senderId;
        this.receiverId = receiverId; // Use receiverId here
        this.messageContent = messageContent;
        this.timestamp = new Date(); // Automatically set the timestamp
        this.readStatus = readStatus;
        this.priority = priority;
    }

    // Default Constructor
    public Message() {}

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isReadStatus() {
        return readStatus;
    }

    public void setReadStatus(boolean readStatus) {
        this.readStatus = readStatus;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}

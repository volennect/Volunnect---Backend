package com.example.chatapp.cht_DTO;

import java.time.LocalDateTime;
import java.util.Objects;

public class MessageDTO {

    private String fromUserId;
    private String toUserId;
    private String messageContent;
    private LocalDateTime timestamp;
    private boolean readStatus;  // Indicates if the message has been read
    private int priority;        // Priority level of the message (e.g., 1-High, 2-Medium, 3-Low)
    private String groupId;      // Add groupId field

    // Default Constructor
    public MessageDTO() {
        this.timestamp = LocalDateTime.now();
        this.readStatus = false; // Default value
        this.priority = 3;       // Default priority (Low)
    }

    // Parameterized Constructor
    public MessageDTO(String fromUserId, String toUserId, String messageContent, boolean readStatus, int priority, String groupId) {
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.messageContent = messageContent;
        this.timestamp = LocalDateTime.now();
        this.readStatus = readStatus;
        this.priority = priority;
        this.groupId = groupId;   // Initialize groupId
    }

    // Getters and Setters
    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
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

    public String getGroupId() {   // Corrected method
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return "MessageDTO{" +
                "fromUserId='" + fromUserId + '\'' +
                ", toUserId='" + toUserId + '\'' +
                ", messageContent='" + messageContent + '\'' +
                ", timestamp=" + timestamp +
                ", readStatus=" + readStatus +
                ", priority=" + priority +
                ", groupId='" + groupId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MessageDTO that = (MessageDTO) obj;
        return readStatus == that.readStatus &&
                priority == that.priority &&
                Objects.equals(fromUserId, that.fromUserId) &&
                Objects.equals(toUserId, that.toUserId) &&
                Objects.equals(messageContent, that.messageContent) &&
                Objects.equals(timestamp, that.timestamp) &&
                Objects.equals(groupId, that.groupId); // Include groupId in equals check
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromUserId, toUserId, messageContent, timestamp, readStatus, priority, groupId);  // Include groupId in hashCode
    }
}

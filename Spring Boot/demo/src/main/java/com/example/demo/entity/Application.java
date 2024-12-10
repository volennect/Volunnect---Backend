package com.example.demo.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "applications")
public class Application {

    @Id
    private String id;
    private String volunteerId;
    private String eventId;
    private String organizationId;
    private String specialNote;
    private String status; // Pending, Accepted, Rejected


    public Application(String volunteerId, String eventId, String organizationId, String specialNote) {
        this.volunteerId = volunteerId;
        this.eventId = eventId;
        this.organizationId = organizationId;
        this.specialNote = specialNote;
        this.status = "Pending"; // Default status
    }

    // Getters and Setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getVolunteerId() {
        return volunteerId;
    }
    public void setVolunteerId(String volunteerId) {
        this.volunteerId = volunteerId;
    }
    public String getEventId() {
        return eventId;
    }
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
    public String getOrganizationId() {
        return organizationId;
    }
    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }
    public String getSpecialNote() {
        return specialNote;
    }
    public void setSpecialNote(String specialNote) {
        this.specialNote = specialNote;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}

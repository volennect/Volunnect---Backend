package com.ApplicationManagement.ApplicationManagement.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "applications")
@Data
public class Application {

    @Id
    private String id;
    private String volunteerId;
    private String eventId;
    private String status; // Pending, Accepted, Rejected
    private String specialNote; // Special note from volunteer
    private String rejectionReason; // Reason if rejected
}

package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "events")
public class Event {

    @Id
    private String id;

    private byte[] image;

    private String title;

    private LocalDate eventDate;  // Date only

    private String description;

    private String location;

    private String type;

    private String skills;

    @Field("registration_deadline")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDate registrationDeadline;  // Use LocalDateTime for date and time

    private List<Shift> shifts;

    public Event(String id, byte[] image, String title, LocalDate eventDate, String description, String location, String type, String skills, LocalDate registrationDeadline, List<Shift> shifts) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.eventDate = eventDate;
        this.description = description;
        this.location = location;
        this.type = type;
        this.skills = skills;
        this.registrationDeadline = registrationDeadline;
        this.shifts = shifts;
    }

    public Event() {
    }

    // Getters and setters for all the fields
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public LocalDate getRegistrationDeadline() {
        return registrationDeadline;
    }

    public void setRegistrationDeadline(LocalDate registrationDeadline) {
        this.registrationDeadline = registrationDeadline;
    }

    public List<Shift> getShifts() {
        return shifts;
    }

    public void setShifts(List<Shift> shifts) {
        this.shifts = shifts;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                ", image=" + image.length + " bytes" +  // Print the image byte length instead of the actual bytes
                ", title='" + title + '\'' +
                ", eventDate=" + eventDate +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", type='" + type + '\'' +
                ", skills='" + skills + '\'' +
                ", registrationDeadline=" + registrationDeadline +
                ", shifts=" + shifts +
                '}';
    }
}

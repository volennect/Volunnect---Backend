package com.example.demo.example.demo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Document(collection = "events")
public class Event {
    @Id
    private String id;
    private String title;
    private LocalDate eventDate;
    private String description;
    private String location;
    private String type;
    private String skills;
    private Date reg_deadline;
    private List<Shift> shifts;

    public Event(String id, String title, LocalDate eventDate, String description, String location, String type, String skills, Date reg_deadline, List<Shift> shifts) {
        this.id = id;
        this.title = title;
        this.eventDate = eventDate;
        this.description = description;
        this.location = location;
        this.type = type;
        this.skills = skills;
        this.reg_deadline = reg_deadline;
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

    public Date getReg_deadline() {
        return reg_deadline;
    }

    public void setReg_deadline(Date reg_deadline) {
        this.reg_deadline = reg_deadline;
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
                ", title='" + title + '\'' +
                ", eventDate=" + eventDate +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", type='" + type + '\'' +
                ", skills='" + skills + '\'' +
                ", reg_deadline=" + reg_deadline +
                ", shifts=" + shifts +
                '}';
    }
}

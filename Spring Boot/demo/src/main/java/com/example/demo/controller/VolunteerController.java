package com.example.demo.controller;

import com.example.demo.entity.Volunteer;
import com.example.demo.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/volunteers")
public class VolunteerController {

    @Autowired
    private VolunteerService volunteerService;

    // Create a new volunteer
    @PostMapping("/create")
    public ResponseEntity<Volunteer> createVolunteer(@RequestBody Volunteer volunteer) {
        try {
            Volunteer savedVolunteer = volunteerService.createVolunteerProfile(volunteer);
            return ResponseEntity.ok(savedVolunteer);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Get all volunteers
    @GetMapping
    public ResponseEntity<List<Volunteer>> getAllVolunteers() {
        List<Volunteer> volunteers = volunteerService.getAllVolunteers();
        return ResponseEntity.ok(volunteers);
    }

    // Get a specific volunteer by ID
    @GetMapping("/{id}")
    public ResponseEntity<Volunteer> getVolunteerById(@PathVariable String id) {
        Volunteer volunteer = volunteerService.getVolunteerById(id);
        if (volunteer != null) {
            return ResponseEntity.ok(volunteer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Update a volunteer profile
    @PutMapping("/{id}")
    public ResponseEntity<Volunteer> updateVolunteer(@PathVariable String id, @RequestBody Volunteer updatedVolunteer) {
        Volunteer volunteer = volunteerService.updateVolunteerProfile(id, updatedVolunteer);
        if (volunteer != null) {
            return ResponseEntity.ok(volunteer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a volunteer by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVolunteer(@PathVariable String id) {
        boolean isDeleted = volunteerService.deleteVolunteerById(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

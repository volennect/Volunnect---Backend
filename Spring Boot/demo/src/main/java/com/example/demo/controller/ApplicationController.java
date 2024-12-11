package com.example.demo.controller;

import com.example.demo.entity.Application;
import com.example.demo.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/applications")
@CrossOrigin(origins="*")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    // Endpoint to apply for an event
    @PostMapping("/apply")
    public ResponseEntity<Application> applyToEvent(@RequestBody Application applicationRequest) {
        Application application = applicationService.createApplication(
                applicationRequest.getVolunteerId(),
                applicationRequest.getEventId(),
                applicationRequest.getOrganizationId(),
                applicationRequest.getSpecialNote()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(application);
    }

    // Endpoint to get applications by volunteer ID
    @GetMapping("/volunteer/{volunteerId}")
    public ResponseEntity<List<Application>> getApplicationsByVolunteer(@PathVariable String volunteerId) {
        List<Application> applications = applicationService.getApplicationsByVolunteer(volunteerId);
        if (applications.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(applications); // No content if empty
        }
        return ResponseEntity.ok(applications);
    }

    // Endpoint to get applications for a specific event
    @GetMapping("/event")
    public ResponseEntity<List<Application>> getApplicationsForEvent(@RequestParam String organizationId,
                                                                     @RequestParam String eventId) {
        List<Application> applications = applicationService.getApplicationsForEvent(organizationId, eventId);
        if (applications.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(applications); // No content if empty
        }
        return ResponseEntity.ok(applications);
    }

    // Endpoint to update the status of an application
    @PutMapping("/{applicationId}/status")
    public ResponseEntity<Application> updateApplicationStatus(@PathVariable String applicationId,
                                                               @RequestParam String status) {
        Application updatedApplication = applicationService.updateApplicationStatus(applicationId, status);
        if (updatedApplication == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Return 404 if application not found
        }
        return ResponseEntity.ok(updatedApplication);
    }
}

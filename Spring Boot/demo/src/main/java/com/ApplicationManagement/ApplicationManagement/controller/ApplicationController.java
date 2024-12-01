package com.ApplicationManagement.ApplicationManagement.controller;

import com.ApplicationManagement.ApplicationManagement.entity.Application;
import com.ApplicationManagement.ApplicationManagement.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping("/apply")
    public Application applyForEvent(@RequestParam String volunteerId,
                                     @RequestParam String eventId,
                                     @RequestParam String specialNote) {
        return applicationService.applyForEvent(volunteerId, eventId, specialNote);
    }

    @GetMapping("/all")
    public List<Application> getAllApplications() {
        return applicationService.getAllApplications();
    }

    @GetMapping("/{id}")
    public Optional<Application> getApplicationById(@PathVariable String id) {
        return applicationService.getApplicationById(id);
    }

    @PutMapping("/update/{id}")
    public Application updateApplicationStatus(@PathVariable String id,
                                               @RequestParam String status,
                                               @RequestParam(required = false) String rejectionReason) {
        return applicationService.updateApplicationStatus(id, status, rejectionReason);
    }
}

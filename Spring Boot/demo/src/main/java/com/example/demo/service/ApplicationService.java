package com.example.demo.service;

import com.example.demo.entity.Application;
import com.example.demo.repo.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    public Application createApplication(String volunteerId, String eventId, String organizationId, String specialNote) {
        Application application = new Application(volunteerId,eventId,organizationId,specialNote);
        application.setVolunteerId(volunteerId);
        application.setEventId(eventId);
        application.setOrganizationId(organizationId);
        application.setSpecialNote(specialNote);
        application.setStatus("Pending");
        return applicationRepository.save(application);
    }

    public List<Application> getApplicationsByVolunteer(String volunteerId) {
        return applicationRepository.findByVolunteerId(volunteerId);
    }

    public List<Application> getApplicationsForEvent(String organizationId, String eventId) {
        return applicationRepository.findByOrganizationIdAndEventId(organizationId, eventId);
    }

    public Application updateApplicationStatus(String applicationId, String status) {
        Application application = applicationRepository.findById(applicationId).orElseThrow(() -> new RuntimeException("Application not found"));
        application.setStatus(status);
        return applicationRepository.save(application);
    }
}

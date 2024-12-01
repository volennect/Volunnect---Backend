package com.ApplicationManagement.ApplicationManagement.service;

import com.ApplicationManagement.ApplicationManagement.entity.Application;
import com.ApplicationManagement.ApplicationManagement.repo.ApplicationRepository;
import com.ApplicationManagement.ApplicationManagement.repo.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private VolunteerRepository volunteerRepository;

    public Application applyForEvent(String volunteerId, String eventId, String specialNote) {
        Application application = new Application();
        application.setVolunteerId(volunteerId);
        application.setEventId(eventId);
        application.setStatus("Pending");
        application.setSpecialNote(specialNote);
        return applicationRepository.save(application);
    }

    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    public Optional<Application> getApplicationById(String id) {
        return applicationRepository.findById(id);
    }

    public Application updateApplicationStatus(String id, String status, String rejectionReason) {
        Optional<Application> application = applicationRepository.findById(id);
        if (application.isPresent()) {
            Application updatedApplication = application.get();
            updatedApplication.setStatus(status);
            if ("Rejected".equals(status)) {
                updatedApplication.setRejectionReason(rejectionReason);
            }
            return applicationRepository.save(updatedApplication);
        }
        return null;
    }
}

package com.example.demo.service;

import com.example.demo.entity.Volunteer;
import com.example.demo.repo.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VolunteerService {

    @Autowired
    private VolunteerRepository volunteerRepository;

    // Create a new volunteer profile
    public Volunteer createVolunteerProfile(Volunteer volunteer) {
        if (volunteerRepository.existsByEmail(volunteer.getEmail())) {
            throw new IllegalArgumentException("A profile with this email already exists.");
        }
        return volunteerRepository.save(volunteer);
    }

    // Get all volunteer profiles
    public List<Volunteer> getAllVolunteers() {
        return volunteerRepository.findAll();
    }

    // Get a specific volunteer profile by ID
    public Volunteer getVolunteerById(String id) {
        Optional<Volunteer> volunteer = volunteerRepository.findById(id);
        return volunteer.orElse(null);
    }

    // Update a volunteer profile
    public Volunteer updateVolunteerProfile(String id, Volunteer updatedVolunteer) {
        Optional<Volunteer> existingVolunteer = volunteerRepository.findById(id);
        if (existingVolunteer.isPresent()) {
            Volunteer volunteer = existingVolunteer.get();
            volunteer.setName(updatedVolunteer.getName());
            volunteer.setEmail(updatedVolunteer.getEmail());
            volunteer.setPhone(updatedVolunteer.getPhone());
            volunteer.setAddress(updatedVolunteer.getAddress());
            volunteer.setDateOfBirth(updatedVolunteer.getDateOfBirth());
            volunteer.setSkills(updatedVolunteer.getSkills());
            volunteer.setAvailability(updatedVolunteer.getAvailability());
            return volunteerRepository.save(volunteer);
        } else {
            return null;
        }
    }

    // Delete a volunteer profile by ID
    public boolean deleteVolunteerById(String id) {
        if (volunteerRepository.existsById(id)) {
            volunteerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

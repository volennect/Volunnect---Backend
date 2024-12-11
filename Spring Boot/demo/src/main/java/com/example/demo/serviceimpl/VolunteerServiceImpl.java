/*package com.example.demo.serviceimpl;

import com.example.demo.entity.Volunteer;
import com.example.demo.repo.VolunteerRepository;
import com.example.demo.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VolunteerServiceImpl implements VolunteerService {

    @Autowired
    private VolunteerRepository volunteerRepository;

    @Override
    public Volunteer createVolunteer(Volunteer volunteer) {
        return volunteerRepository.save(volunteer);
    }

    @Override
    public List<Volunteer> getAllVolunteers() {
        return volunteerRepository.findAll();
    }

    @Override
    public Volunteer getVolunteerById(String id) {
        Optional<Volunteer> volunteer = volunteerRepository.findById(id);
        return volunteer.orElse(null); // Return null if not found
    }

    @Override
    public Volunteer updateVolunteer(String id, Volunteer updatedVolunteer) {
        Optional<Volunteer> existingVolunteer = volunteerRepository.findById(id);

        if (existingVolunteer.isPresent()) {
            Volunteer volunteer = existingVolunteer.get();
            // Update fields
            if (updatedVolunteer.getName() != null) volunteer.setName(updatedVolunteer.getName());
            if (updatedVolunteer.getEmail() != null) volunteer.setEmail(updatedVolunteer.getEmail());
            if (updatedVolunteer.getPhone() != null) volunteer.setPhone(updatedVolunteer.getPhone());
            if (updatedVolunteer.getAddress() != null) volunteer.setAddress(updatedVolunteer.getAddress());
            if (updatedVolunteer.getDateOfBirth() != null) volunteer.setDateOfBirth(updatedVolunteer.getDateOfBirth());
            if (updatedVolunteer.getInterests() != null) volunteer.setInterests(updatedVolunteer.getInterests());
            if (updatedVolunteer.getUnavailableDates() != null) volunteer.setUnavailableDates(updatedVolunteer.getUnavailableDates());

            return volunteerRepository.save(volunteer);
        }

        return null;
    }

    @Override
    public boolean deleteVolunteer(String id) {
        if (volunteerRepository.existsById(id)) {
            volunteerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
*/
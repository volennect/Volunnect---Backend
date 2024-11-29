package com.example.demo.service;

import com.example.demo.entity.Volunteer;

import java.util.List;

public interface VolunteerService {
    Volunteer createVolunteer(Volunteer volunteer);

    List<Volunteer> getAllVolunteers();

    Volunteer getVolunteerById(String id);

    Volunteer updateVolunteer(String id, Volunteer updatedVolunteer);

    boolean deleteVolunteer(String id);
}

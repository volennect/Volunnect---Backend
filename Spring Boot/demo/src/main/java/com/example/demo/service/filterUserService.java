package com.example.demo.service;

import com.example.demo.entity.FilterUsers;

import java.time.LocalDate;
import java.util.List;

public interface filterUserService {
    String save(FilterUsers filterUsers);

    Iterable<FilterUsers> listAll(FilterUsers filterUsers);

    FilterUsers getUserById(String userId);

    List<String> getUserInterests(String userId);

    List<LocalDate> getUnavailableDates(String userId);

    List<FilterUsers> getAllUsers();
}

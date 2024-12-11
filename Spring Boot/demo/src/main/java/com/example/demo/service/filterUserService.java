package com.example.demo.service;

import com.example.demo.entity.FilterUsers;

import java.time.LocalDate;
import java.util.List;

public interface filterUserService {

    Long save(FilterUsers filterUsers);

    FilterUsers getUserById(Long userId);

    List<String> getUserInterests(Long userId);

    List<LocalDate> getUnavailableDates(Long userId);
}

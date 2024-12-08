package com.example.demo.service;

import com.example.demo.entity.FilterUsers;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repo.filterUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class filterUserServiceImpl implements filterUserService {

    @Autowired
    private filterUserRepo filterUserRepo;

    @Override
    public String save(FilterUsers filterUsers) {
        return filterUserRepo.save(filterUsers).getUserId();
    }

    @Override
    public List<FilterUsers> listAll(FilterUsers filterUsers) {
        return filterUserRepo.findAll();
    }

    @Override
    public FilterUsers getUserById(String userId) {
        return filterUserRepo.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public List<String> getUserInterests(String userId) {
        FilterUsers user = getUserById(userId);
        return user.getInterests();
    }

    @Override
    public List<LocalDate> getUnavailableDates(String userId) {
        FilterUsers user = getUserById(userId);
        return user.getUnavailableDates();
    }

}

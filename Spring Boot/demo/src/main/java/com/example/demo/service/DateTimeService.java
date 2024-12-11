package com.example.demo.service;

import com.example.demo.entity.DateTimeEntity;
import com.example.demo.repo.DateTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DateTimeService {

    @Autowired
    private DateTimeRepository repository;

    public DateTimeEntity createDateTime(DateTimeEntity dateTimeEntity) {
        return repository.save(dateTimeEntity);
    }

    public List<DateTimeEntity> getAllDateTimes() {
        return repository.findAll();
    }

    public Optional<DateTimeEntity> getDateTimeById(String id) {
        return repository.findById(id);
    }

    public DateTimeEntity updateDateTime(String id, DateTimeEntity updatedEntity) {
        return repository.findById(id).map(existingEntity -> {
            existingEntity.setDescription(updatedEntity.getDescription());
            existingEntity.setDate(updatedEntity.getDate());
            existingEntity.setDateTime(updatedEntity.getDateTime());
            return repository.save(existingEntity);
        }).orElseThrow(() -> new RuntimeException("DateTimeEntity not found"));
    }

    public void deleteDateTime(String id) {
        repository.deleteById(id);
    }

    public List<DateTimeEntity> getRecordsByDate(LocalDate date) {
        return repository.findByDate(date);
    }
}

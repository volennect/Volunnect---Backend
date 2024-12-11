package com.example.demo.controller;

import com.example.demo.entity.DateTimeEntity;
import com.example.demo.service.DateTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/date-time")
public class DateTimeController {

    @Autowired
    private DateTimeService service;

    @PostMapping
    public ResponseEntity<DateTimeEntity> createDateTime(@RequestBody DateTimeEntity dateTimeEntity) {
        return ResponseEntity.ok(service.createDateTime(dateTimeEntity));
    }

    @GetMapping
    public ResponseEntity<List<DateTimeEntity>> getAllDateTimes() {
        return ResponseEntity.ok(service.getAllDateTimes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DateTimeEntity> getDateTimeById(@PathVariable String id) {
        return service.getDateTimeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DateTimeEntity> updateDateTime(
            @PathVariable String id,
            @RequestBody DateTimeEntity updatedEntity) {
        return ResponseEntity.ok(service.updateDateTime(id, updatedEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDateTime(@PathVariable String id) {
        service.deleteDateTime(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-date")
    public ResponseEntity<List<DateTimeEntity>> getRecordsByDate(@RequestParam("date") String date) {
        // Convert the String parameter to LocalDate
        LocalDate localDate = LocalDate.parse(date);
        List<DateTimeEntity> records = service.getRecordsByDate(localDate);
        return ResponseEntity.ok(records);
    }
}

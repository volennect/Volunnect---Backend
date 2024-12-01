package com.example.demo.controller;

import com.example.demo.entity.Attendance;
import com.example.demo.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendances")
@CrossOrigin(origins = "http://localhost:5173")  // Replace with your Vite React frontend's address
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @GetMapping
    public List<Attendance> getAllAttendances() {
        return attendanceService.getAllAttendances();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Attendance> getAttendanceById(@PathVariable String id) {
        Attendance attendance = attendanceService.getAttendanceById(id);
        if (attendance == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(attendance);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Attendance> updateAttendance(
            @PathVariable String id,
            @RequestBody Attendance updatedAttendance) {
        // Fetch the existing attendance record
        Attendance existingAttendance = attendanceService.getAttendanceById(id);

        if (existingAttendance == null) {
            return ResponseEntity.notFound().build();
        }

        // Update the fields of the existing attendance record with the new data
        existingAttendance.setEventName(updatedAttendance.getEventName());
        existingAttendance.setStudents(updatedAttendance.getStudents());

        // Save the updated attendance record
        Attendance savedAttendance = attendanceService.saveAttendance(existingAttendance);

        return ResponseEntity.ok(savedAttendance);
    }


    @PostMapping
    public Attendance createAttendance(@RequestBody Attendance attendance) {
        return attendanceService.saveAttendance(attendance);
    }
}

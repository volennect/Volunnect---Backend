package com.example.demo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@Document(collection = "attendance")
public class Attendance {

    @Id
    private String id;
    private String eventName;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Student> students;


    @Data
    public static class Student {
        private int roll;
        private String name;

        private List<Boolean> attendance;
    }
}

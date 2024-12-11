package com.example.demo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Document(collection = "date_time")
public class DateTimeEntity {
    @Id
    private String id;

    private String description;
    private LocalDate date = LocalDate.now(); // For LocalDate
    private LocalDateTime dateTime = LocalDateTime.now(); // For LocalDateTime
}

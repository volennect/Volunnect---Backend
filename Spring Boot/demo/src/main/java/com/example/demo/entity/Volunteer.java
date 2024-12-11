package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Document(collection = "volunteers")
public class Volunteer {
    @Id
    private String id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String dateOfBirth;
    private List<String> interests;
    private List<LocalDate> unavailableDates;
    private Double ratings;
}
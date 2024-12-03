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
@Document(collection = "Users")
public class FilterUsers {
    @Id
    private Long userId;
    private String name;
    private String age;
    private List<String> interests;
    private List<LocalDate> unavailableDates;
    private Double ratings;
}

package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Document(collection = "Events")
public class Event {
    @Id
    private String id;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
    private String location;
    private String type;
    private String req_skills;
    private Date reg_deadline;
}

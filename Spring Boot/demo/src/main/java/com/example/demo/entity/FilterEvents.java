package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Events")
public class FilterEvents {
    @Id
    private Long eventId;
    private String EventName;
    private String EventType;
    private LocalDate eventStartDate;
    private LocalDate eventEndDate;
}

package com.example.demo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "file_metadata")
public class FileMetadata {

    @Id
    private String id;
    private String volunteerId;
    private String eventId;
    private LocalDate date = LocalDate.now();
    private String fileName;
    private String contentType;
    private long size;
}

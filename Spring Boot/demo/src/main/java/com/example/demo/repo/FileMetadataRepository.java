package com.example.demo.repo;

import com.example.demo.entity.FileMetadata;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FileMetadataRepository extends MongoRepository<FileMetadata, String> {
    List<FileMetadata> findByVolunteerId(String volunteerId);
}

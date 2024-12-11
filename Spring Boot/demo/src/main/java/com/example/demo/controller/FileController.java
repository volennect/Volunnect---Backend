package com.example.demo.controller;

import com.example.demo.entity.FileMetadata;
import com.example.demo.repo.FileMetadataRepository;
import com.example.demo.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/files")
@CrossOrigin(origins = "http://localhost:5173")
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private FileMetadataRepository fileMetadataRepository;

    @PostMapping("/upload")
    public ResponseEntity<FileMetadata> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("volunteerId") String volunteerId,
            @RequestParam("eventId") String eventId,
            @RequestParam("day") String day) {
        try {
            FileMetadata metadata = fileService.uploadFile(file, volunteerId, eventId, day);
            return ResponseEntity.ok(metadata);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String id) {
        try {
            Optional<FileMetadata> metadataOpt = fileService.getFileMetadata(id);
            if (metadataOpt.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            FileMetadata metadata = metadataOpt.get();
            byte[] fileData = fileService.downloadFile(id);

            // Build headers with metadata information
            HttpHeaders headers = new HttpHeaders();
            headers.setContentDispositionFormData("attachment", metadata.getFileName());
            headers.setContentType(MediaType.parseMediaType(metadata.getContentType()));
            headers.setContentLength(metadata.getSize());

            // Include additional metadata in custom headers

            headers.add("X-Volunteer-Id", metadata.getVolunteerId());
            headers.add("X-Event-Id", metadata.getEventId());
            headers.add("X-Day", String.valueOf(metadata.getDate()));
            headers.add("X-FileName", metadata.getFileName());


            return ResponseEntity.ok()
                    .headers(headers)
                    .body(fileData);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFile(@PathVariable String id) {
        fileService.deleteFile(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<FileMetadata> updateFileMetadata(
            @PathVariable String id,
            @RequestParam("volunteerId") String volunteerId,
            @RequestParam("eventId") String eventId) {
        try {
            Optional<FileMetadata> metadataOpt = fileService.getFileMetadata(id);
            if (metadataOpt.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            FileMetadata metadata = metadataOpt.get();
            metadata.setVolunteerId(volunteerId);
            metadata.setEventId(eventId);


            FileMetadata updatedMetadata = fileService.updateFileMetadata(metadata);
            return ResponseEntity.ok(updatedMetadata);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }

    }
    @GetMapping("/search")
    public List<FileMetadata> getByVolunteerId(@RequestParam String volunteerId) {
        return fileMetadataRepository.findByVolunteerId(volunteerId);
    }
}

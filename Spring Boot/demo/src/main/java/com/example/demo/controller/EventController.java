package com.example.demo.controller;

import com.example.demo.config.FileUploadConfig;
import com.example.demo.entity.Event;
import com.example.demo.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("api/v1/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private FileUploadConfig fileUploadConfig;



    // Endpoint for saving event data without image (using JSON)
    @PostMapping(value = "/save")
    public String saveEvent(@RequestBody Event event) {
        eventService.saveOrUpdate(event);
        return event.getId();
    }

    // Endpoint for saving event data with image (Multipart request)
    @PostMapping(value = "/saveWithImage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> saveEventWithImage(
            @RequestPart("event") Event event,
            @RequestPart("file") MultipartFile file) {
        try {
            // Get the upload directory from the configuration
            String uploadDir = fileUploadConfig.uploadDirectory();

            // Save the file locally
            if (!file.isEmpty()) {
                // Generate a unique file name using the event ID
                String fileName = event.getId() + "_" + file.getOriginalFilename();
                Path filePath = Paths.get(uploadDir).resolve(fileName);

                // Create directories if they don't exist
                Files.createDirectories(filePath.getParent());

                // Write the file to the server's disk
                Files.write(filePath, file.getBytes());

                // Save the image file name or URL in the event object
                event.setImage(file.getBytes()); // Storing only the file name or path, not the bytes
            }

            // Save the event to the database
            eventService.saveOrUpdate(event);

            // Return the event ID or a success message
            return ResponseEntity.ok("Event saved with ID: " + event.getId());
        } catch (IOException e) {
            // Handle any errors during the file upload process
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error while uploading event and image: " + e.getMessage());
        }
    }

    // Get event by ID
    @GetMapping("/getById/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable("id") String eventId) {
        Event event = eventService.getEventById(eventId);

        if (event != null) {
            return ResponseEntity.ok(event);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }




    // Get all events
    @GetMapping(value = "/getall")
    public Iterable<Event> getEvents() {
        return eventService.listAll();
    }

    // Update an event by ID
    @PutMapping(value = "/edit/{id}")
    public Event update(@RequestBody Event event, @PathVariable(name = "id") String id) {
        event.setId(id);
        return  eventService.saveOrUpdate(event);

    }

    // Delete an event by ID
    @DeleteMapping("/delete/{id}")
    public void deleteEvent(@PathVariable("id") String id) {
        eventService.deleteEvent(id);
    }

}

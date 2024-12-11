package com.example.demo.service;

import com.example.demo.entity.FileMetadata;
import com.example.demo.repo.FileMetadataRepository;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Service
public class FileService {

    @Autowired
    private FileMetadataRepository fileMetadataRepository;

    @Autowired
    private GridFSBucket gridFSBucket;

    public FileMetadata uploadFile(MultipartFile file, String volunteerId, String eventId, String day) throws IOException {
        // Store file in GridFS
        InputStream fileStream = file.getInputStream();
        GridFSUploadOptions options = new GridFSUploadOptions()
                .metadata(new org.bson.Document("contentType", file.getContentType()));

        ObjectId fileId = gridFSBucket.uploadFromStream(file.getOriginalFilename(), fileStream, options);

        // Save file metadata
        FileMetadata metadata = new FileMetadata();
        metadata.setId(fileId.toString());
        metadata.setFileName(file.getOriginalFilename());
        metadata.setContentType(file.getContentType());
        metadata.setSize(file.getSize());
        metadata.setVolunteerId(volunteerId);
        metadata.setEventId(eventId);


        return fileMetadataRepository.save(metadata);
    }

    public FileMetadata updateFileMetadata(FileMetadata metadata) {
        return fileMetadataRepository.save(metadata); // Assuming you're using Spring Data MongoDB
    }



    public Optional<FileMetadata> getFileMetadata(String id) {
        return fileMetadataRepository.findById(id);
    }

    public byte[] downloadFile(String id) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        gridFSBucket.downloadToStream(new ObjectId(id), outputStream);
        return outputStream.toByteArray();
    }

    public void deleteFile(String id) {
        gridFSBucket.delete(new ObjectId(id));
        fileMetadataRepository.deleteById(id);
    }
}

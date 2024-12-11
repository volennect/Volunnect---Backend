package com.example.demo.repo;

import com.example.demo.entity.DateTimeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DateTimeRepository extends MongoRepository<DateTimeEntity, String> {
    List<DateTimeEntity> findByDate(LocalDate date); // Query method for filtering by LocalDate
}

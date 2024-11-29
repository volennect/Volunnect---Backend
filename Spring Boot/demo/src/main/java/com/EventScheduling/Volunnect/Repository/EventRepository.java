package com.EventScheduling.Volunnect.Repository;

import com.EventScheduling.Volunnect.Entity.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository <Event, String> {
}

package com.city.services.urbanevents.repository;


import com.city.services.urbanevents.model.UrbanEvent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UrbanEventRepository extends MongoRepository<UrbanEvent, String> {
    List<UrbanEvent> findByCategory(String category);
    List<UrbanEvent> findByLocation(String location);
    List<UrbanEvent> findByStartDateTimeAfter(LocalDateTime date);
}
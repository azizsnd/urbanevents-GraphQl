package com.city.services.urbanevents.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "urban_events")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UrbanEvent {
    @Id
    private String id;
    private String title;
    private String description;
    private String location;
    private String category;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startDateTime;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endDateTime;

    private List<String> participants;
    private boolean isFree;
}
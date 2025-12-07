package com.city.services.urbanevents.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class UrbanEventInput {
    private String title;
    private String description;
    private String location;
    private String category;
    private String startDateTime;  // En String
    private String endDateTime;    // En String
    private List<String> participants;
    private Boolean isFree;
}
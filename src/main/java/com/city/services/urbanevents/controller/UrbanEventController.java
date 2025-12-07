package com.city.services.urbanevents.controller;


import com.city.services.urbanevents.dto.UrbanEventInput;
import com.city.services.urbanevents.model.UrbanEvent;
import com.city.services.urbanevents.repository.UrbanEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.time.format.DateTimeFormatter;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UrbanEventController {

    private final UrbanEventRepository repository;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    @QueryMapping
    public List<UrbanEvent> events() {
        return repository.findAll();
    }

    @QueryMapping
    public UrbanEvent event(@Argument String id) {
        return repository.findById(id).orElse(null);
    }

    @QueryMapping
    public List<UrbanEvent> eventsByCategory(@Argument String category) {
        return repository.findByCategory(category);
    }

    @QueryMapping
    public List<UrbanEvent> eventsByLocation(@Argument String location) {
        return repository.findByLocation(location);
    }

    @QueryMapping
    public List<UrbanEvent> upcomingEvents() {
        return repository.findByStartDateTimeAfter(LocalDateTime.now());
    }

    @MutationMapping
    public UrbanEvent createEvent(@Argument("input") UrbanEventInput input) {
        log.info("Creating event with input: {}", input);

        try {
            // Validation des champs obligatoires
            if (input.getTitle() == null || input.getTitle().isEmpty()) {
                throw new IllegalArgumentException("Title is required");
            }
            if (input.getLocation() == null || input.getLocation().isEmpty()) {
                throw new IllegalArgumentException("Location is required");
            }
            if (input.getCategory() == null || input.getCategory().isEmpty()) {
                throw new IllegalArgumentException("Category is required");
            }
            if (input.getStartDateTime() == null || input.getStartDateTime().isEmpty()) {
                throw new IllegalArgumentException("StartDateTime is required");
            }

            // Conversion des dates
            LocalDateTime startDateTime = LocalDateTime.parse(input.getStartDateTime(), FORMATTER);

            LocalDateTime endDateTime = null;
            if (input.getEndDateTime() != null && !input.getEndDateTime().isEmpty()) {
                endDateTime = LocalDateTime.parse(input.getEndDateTime(), FORMATTER);
            }

            // Création de l'objet UrbanEvent
            UrbanEvent event = UrbanEvent.builder()
                    .title(input.getTitle())
                    .description(input.getDescription())
                    .location(input.getLocation())
                    .category(input.getCategory())
                    .startDateTime(startDateTime)
                    .endDateTime(endDateTime)
                    .participants(input.getParticipants())
                    .isFree(input.getIsFree() != null ? input.getIsFree() : false)
                    .build();

            log.info("Saving event: {}", event);
            UrbanEvent savedEvent = repository.save(event);
            log.info("Event saved with ID: {}", savedEvent.getId());

            return savedEvent;

        } catch (DateTimeParseException e) {
            log.error("Invalid date format: {}", e.getMessage());
            throw new IllegalArgumentException("Invalid date format. Use ISO format: yyyy-MM-dd'T'HH:mm:ss");
        } catch (Exception e) {
            log.error("Error creating event: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to create event: " + e.getMessage());
        }
    }

    @MutationMapping
    public Boolean deleteEvent(@Argument String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
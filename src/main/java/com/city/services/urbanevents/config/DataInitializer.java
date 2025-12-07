package com.city.services.urbanevents.config;


import com.city.services.urbanevents.model.UrbanEvent;
import com.city.services.urbanevents.repository.UrbanEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UrbanEventRepository eventRepository;

    @Override
    public void run(String... args) {
        log.info("Initializing database with sample data...");

        // Vérifie si la base est déjà peuplée
        if (eventRepository.count() == 0) {
            List<UrbanEvent> events = createSampleEvents();
            eventRepository.saveAll(events);
            log.info("✅ {} sample events inserted into database", events.size());
        } else {
            log.info("✅ Database already contains {} events", eventRepository.count());
        }
    }

    private List<UrbanEvent> createSampleEvents() {
        return Arrays.asList(
                UrbanEvent.builder()
                        .title("Concert Jazz en Plein Air")
                        .description("Un magnifique concert de jazz avec des artistes locaux")
                        .location("Place de la Mairie")
                        .category("Concert")
                        .startDateTime(LocalDateTime.now().plusDays(1).withHour(19).withMinute(0))
                        .endDateTime(LocalDateTime.now().plusDays(1).withHour(22).withMinute(0))
                        .participants(Arrays.asList("Orchestre Municipal", "Soliste Invité"))
                        .isFree(true)
                        .build(),

                UrbanEvent.builder()
                        .title("Marché Bio du Dimanche")
                        .description("Produits locaux et biologiques")
                        .location("Place du Marché")
                        .category("Marché")
                        .startDateTime(LocalDateTime.now().plusDays(2).withHour(8).withMinute(0))
                        .endDateTime(LocalDateTime.now().plusDays(2).withHour(14).withMinute(0))
                        .participants(Arrays.asList("Producteurs Locaux", "Association Bio"))
                        .isFree(true)
                        .build(),

                UrbanEvent.builder()
                        .title("Festival des Lumières")
                        .description("Spectacle de lumières et mapping vidéo")
                        .location("Parc Central")
                        .category("Spectacle")
                        .startDateTime(LocalDateTime.now().plusDays(3).withHour(20).withMinute(30))
                        .endDateTime(LocalDateTime.now().plusDays(3).withHour(23).withMinute(30))
                        .participants(Arrays.asList("Artistes Visuels", "Ville de Paris"))
                        .isFree(false)
                        .build(),

                UrbanEvent.builder()
                        .title("Réunion Conseil de Quartier")
                        .description("Réunion publique pour discuter des projets du quartier")
                        .location("Salle Polyvalente")
                        .category("Réunion")
                        .startDateTime(LocalDateTime.now().plusHours(2))
                        .endDateTime(LocalDateTime.now().plusHours(4))
                        .participants(Arrays.asList("Maire Adjoint", "Conseillers Municipaux"))
                        .isFree(true)
                        .build(),

                UrbanEvent.builder()
                        .title("Travaux Rue Principale")
                        .description("Rénovation de la chaussée et des trottoirs")
                        .location("Rue Principale")
                        .category("Travaux")
                        .startDateTime(LocalDateTime.now().minusDays(1))
                        .endDateTime(LocalDateTime.now().plusDays(5))
                        .participants(Arrays.asList("Services Techniques", "Entreprise BTP"))
                        .isFree(true)
                        .build()
        );
    }
}
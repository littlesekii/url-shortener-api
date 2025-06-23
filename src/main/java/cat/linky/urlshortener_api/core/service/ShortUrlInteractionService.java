package cat.linky.urlshortener_api.core.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cat.linky.urlshortener_api.core.model.ShortUrlInteraction;
import cat.linky.urlshortener_api.core.model.dto.ChartViewDayDTO;
import cat.linky.urlshortener_api.core.model.dto.ShortUrlInteractionDTO;
import cat.linky.urlshortener_api.core.repository.IShortUrlInteractionRepository;

@Service
public class ShortUrlInteractionService {
    
    private IShortUrlInteractionRepository repository;

    public ShortUrlInteractionService(IShortUrlInteractionRepository repository) {
        this.repository = repository;
    }

    public void create(Long idShortUrl) {
        repository.save(new ShortUrlInteraction(idShortUrl, Instant.now()));
    }

    public List<ShortUrlInteractionDTO> findAllByIdShortUrl(Long idShortUrl) {
        List<ShortUrlInteraction> data = repository.findAllByIdShortUrl(idShortUrl);

        List<ShortUrlInteractionDTO> result =  data.stream().map(
            interaction -> new ShortUrlInteractionDTO(
                interaction.getId(), 
                interaction.getIdShortUrl(), 
                interaction.getMoment()
            )
        ).collect(Collectors.toList());        

        return result;
    }

    public ChartViewDayDTO chartViewDay(Long idShortUrl, LocalDate day, String zone) {
        List<String> label = new ArrayList<>();
        List<String> value = new ArrayList<>();

        List<ShortUrlInteraction> data = repository.findAllByIdShortUrlAndMomentBetween(
            idShortUrl, 
            day.atStartOfDay(ZoneId.of(zone)).toInstant(), 
            day.plusDays(1).atStartOfDay(ZoneId.of(zone)).toInstant()
        );

        for (int i = 0; i < 24; i++) {
            final int hour = i;
            label.add(LocalTime.of(hour, 0).toString());
            value.add(String.valueOf(data.stream()
                .filter(item -> item.getMoment()
                    .atZone(ZoneId.of(zone))
                    .toLocalTime()
                    .isAfter(LocalTime.of(hour, 0)))
                .filter(item -> item.getMoment()
                    .atZone(ZoneId.of(zone))
                    .toLocalTime()
                    .isBefore(LocalTime.of(hour, 59, 59)))
                .count() 
            ));
        }
        return new ChartViewDayDTO(label, value);
    }

}

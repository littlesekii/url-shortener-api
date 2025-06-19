package cat.linky.urlshortener_api.core.model.dto;

import java.time.Instant;

import cat.linky.urlshortener_api.core.model.ShortUrlInteraction;

public record ShortUrlInteractionDTO(Long id, Long idShortUrl, Instant moment) {

    ShortUrlInteractionDTO fromEntity(ShortUrlInteraction entity) {
        return new ShortUrlInteractionDTO(
            entity.getId(), 
            entity.getIdShortUrl(), 
            entity.getMoment()
        );
    }
}

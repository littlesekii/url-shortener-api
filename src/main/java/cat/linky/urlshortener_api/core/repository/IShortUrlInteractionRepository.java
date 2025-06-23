package cat.linky.urlshortener_api.core.repository;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cat.linky.urlshortener_api.core.model.ShortUrlInteraction;

@Repository
public interface IShortUrlInteractionRepository extends JpaRepository<ShortUrlInteraction, Long> {
    List<ShortUrlInteraction> findAllByIdShortUrl(Long idShortUrl);

    List<ShortUrlInteraction> findAllByIdShortUrlAndMomentBetween(
        Long idShortUrl, 
        Instant startDateTime, 
        Instant endDateTime
    );
}

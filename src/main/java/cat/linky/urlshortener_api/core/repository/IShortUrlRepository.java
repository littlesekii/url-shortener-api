package cat.linky.urlshortener_api.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cat.linky.urlshortener_api.core.model.ShortUrl;

@Repository
public interface IShortUrlRepository extends JpaRepository<ShortUrl, Long> {
    ShortUrl findByShortHash(String shortHash);
    ShortUrl findByShortUrl(String shortUrl);
}

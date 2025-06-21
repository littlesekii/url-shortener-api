package cat.linky.urlshortener_api.core.model.dto;

import java.time.Instant;

import cat.linky.urlshortener_api.core.model.ShortUrl;

public record ShortUrlDTO(Long id, String shortHash, String shortUrl, String targetUrl, Instant createdAt) {
    
    public static ShortUrlDTO fromEntity(ShortUrl entity) {
        return new ShortUrlDTO(
            entity.getId(), 
            entity.getShortHash(),
            entity.getShortUrl(), 
            entity.getTargetUrl(),
            entity.getCreatedAt()
        );
    }

    public ShortUrl toEntity() {
        return new ShortUrl(
            null, 
            shortHash,
            shortUrl, 
            targetUrl,
            createdAt
        );
    }
}

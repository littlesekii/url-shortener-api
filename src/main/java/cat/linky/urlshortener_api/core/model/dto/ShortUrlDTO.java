package cat.linky.urlshortener_api.core.model.dto;

import cat.linky.urlshortener_api.core.model.ShortUrl;

public record ShortUrlDTO(Long id, String shortHash, String shortUrl, String targetUrl) {
    
    public static ShortUrlDTO fromEntity(ShortUrl entity) {
        return new ShortUrlDTO(
            entity.getId(), 
            entity.getShortHash(),
            entity.getShortUrl(), 
            entity.getTargetUrl()
        );
    }

    public ShortUrl toEntity() {
        return new ShortUrl(
            null, 
            shortHash,
            shortUrl, 
            targetUrl
        );
    }
}

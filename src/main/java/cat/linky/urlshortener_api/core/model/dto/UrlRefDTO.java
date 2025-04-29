package cat.linky.urlshortener_api.core.model.dto;

import cat.linky.urlshortener_api.core.model.UrlRef;

public record UrlRefDTO(Long id, String urlRef, String urlDest) {
    
    public static UrlRefDTO fromEntity(UrlRef entity) {
        return new UrlRefDTO(
            entity.getId(), 
            entity.getUrlRef(), 
            entity.getUrlDest()
        );
    }

    public UrlRef toEntity() {
        return new UrlRef(
            null, 
            urlRef, 
            urlDest
        );
    }
}

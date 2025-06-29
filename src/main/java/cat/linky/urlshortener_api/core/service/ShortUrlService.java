package cat.linky.urlshortener_api.core.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cat.linky.urlshortener_api.core.model.ShortUrl;
import cat.linky.urlshortener_api.core.model.dto.ShortUrlDTO;
import cat.linky.urlshortener_api.core.repository.IShortUrlRepository;
import cat.linky.urlshortener_api.core.util.Utils;

@Service
public class ShortUrlService {
    
    @Value("${server.url}")
    private String SERVER_BASE_URL;

    @Value("${client.url}")
    private String CLIENT_BASE_URL;

    private IShortUrlRepository repository;

    public ShortUrlService(IShortUrlRepository repository) {
        this.repository = repository;
    }

    public ShortUrlDTO create(ShortUrlDTO data) {
        String shortUrl;
        //prevent duplicate shortened url
        do {
            shortUrl = createDynamicUrl(SERVER_BASE_URL);
        } while (repository.findByShortUrl(shortUrl) != null);
        

        var entity = data.toEntity();
        entity.setShortHash(shortUrl.replace(SERVER_BASE_URL, ""));
        entity.setShortUrl(shortUrl);
        entity.setCreatedAt(Instant.now());

        var created = repository.save(entity);
        ShortUrlDTO result = ShortUrlDTO.fromEntity(created);
        
        return result;
    }

    public ShortUrlDTO findByShortHash(String shortHash) {
        return findEntityOrDefault(repository.findByShortHash(shortHash));
    }

    public ShortUrlDTO findByShortUrl(String shortUrl) {
        return findEntityOrDefault(repository.findByShortUrl(shortUrl));
    }

    private ShortUrlDTO findEntityOrDefault(ShortUrl entity) {
        if (entity == null) {
            return new ShortUrlDTO(null, "", "", CLIENT_BASE_URL, null);
        }

        ShortUrlDTO result = ShortUrlDTO.fromEntity(entity);

        return result;
    }

    public String normalizeTargetUrl(String targetUrl) {
        if ((targetUrl == null || targetUrl.trim().isEmpty())) 
            return null;
        
        targetUrl = targetUrl.trim();

        if ((!Utils.startsWithAlphanumeric(targetUrl)) || (!targetUrl.contains(".")))
            return null;

        if (!targetUrl.startsWith("http://") && !targetUrl.startsWith("https://"))
            return "https://" + targetUrl;

        return targetUrl;
    }

    private String createDynamicUrl(String baseUrl) {
        StringBuilder result = new StringBuilder();
        result.append(baseUrl);

        int ranges[][] = {
            {48, 57}, // '0 to '9' char
            {65, 90}, // 'A' to 'Z' char
            {97, 122} // 'a' to 'z' char
        };

        for(int i = 0; i < 5; i++)
        {
            int randomRangeIndex = Utils.randomInt(0, 2);
            int range[] = ranges[randomRangeIndex];

            int randomCharInt = Utils.randomInt(range[0], range[1]);
            result.append((char)randomCharInt);
        }

        return result.toString();
    }
}

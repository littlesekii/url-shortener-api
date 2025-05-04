package cat.linky.urlshortener_api.core.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cat.linky.urlshortener_api.core.model.dto.UrlRefDTO;
import cat.linky.urlshortener_api.core.repository.IUrlRefRepository;
import cat.linky.urlshortener_api.core.util.Utils;

@Service
public class UrlRefService {
    
    @Value("${server.baseurl}")
    private String BASE_URL;

    private IUrlRefRepository repository;

    public UrlRefService(IUrlRefRepository repository) {
        this.repository = repository;
    }

    public UrlRefDTO create(UrlRefDTO data) {
        UrlRefDTO result;

        String urlRef;
        //prevent duplicate url refs
        do {
            urlRef = createDynamicUrl(BASE_URL);
            // System.out.println(repository.findByUrlRef(urlRef));
        } while (repository.findByUrlRef(urlRef) != null);
        

        var entity = data.toEntity();
        entity.setUrlRef(urlRef.toString());

        var created = repository.save(entity);
        result = UrlRefDTO.fromEntity(created);
        
        return result;
    }

    public UrlRefDTO findByUrlRef(String urlRef) {
        UrlRefDTO result;        

        var entity = repository.findByUrlRef(BASE_URL + urlRef);
        if (entity == null) {
            return new UrlRefDTO(null, "", "https://shorturl.linky.cat");
        }

        result = UrlRefDTO.fromEntity(entity);
        return result;
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

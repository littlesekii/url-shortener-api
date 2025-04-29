package cat.linky.urlshortener_api.core.service;

import org.springframework.stereotype.Service;

import cat.linky.urlshortener_api.core.model.dto.UrlRefDTO;
import cat.linky.urlshortener_api.core.repository.IUrlRefRepository;
import cat.linky.urlshortener_api.core.util.Utils;

@Service
public class UrlRefService {
    
    private IUrlRefRepository repository;

    public UrlRefService(IUrlRefRepository repository) {
        this.repository = repository;
    }

    public UrlRefDTO create(UrlRefDTO data) {
        UrlRefDTO result;

        StringBuilder dinamicUrl = new StringBuilder();
        dinamicUrl.append("http://localhost:1001/");

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
            dinamicUrl.append((char)randomCharInt);
        }

        var entity = data.toEntity();
        entity.setUrlRef(dinamicUrl.toString());

        var created = repository.save(entity);
        result = UrlRefDTO.fromEntity(created);
        
        return result;
    }
}

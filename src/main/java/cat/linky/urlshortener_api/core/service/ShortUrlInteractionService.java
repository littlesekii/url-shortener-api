package cat.linky.urlshortener_api.core.service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cat.linky.urlshortener_api.core.model.ShortUrlInteraction;
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

}

package cat.linky.urlshortener_api.core.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.servlet.view.RedirectView;

import cat.linky.urlshortener_api.core.model.dto.ShortUrlDTO;
import cat.linky.urlshortener_api.core.service.ShortUrlService;

@RestController
@RequestMapping()
public class MainController {

    @Value("${client.url}")
    private String CLIENT_BASE_URL;

    private ShortUrlService service;

    public MainController(ShortUrlService service) {
        this.service = service;
    }
    
    @PostMapping("/api/shorten")
    public ResponseEntity<ShortUrlDTO> post(@RequestBody ShortUrlDTO req) {
        ShortUrlDTO res;

        if(req.targetUrl() == null) {
            return ResponseEntity.badRequest().build();
        }

        res = service.create(req);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(res.id())
            .toUri();

        return ResponseEntity.created(uri).body(res);
    }

    @GetMapping("/{req}")
    public RedirectView get(@PathVariable String req) {
        ShortUrlDTO data = service.findByShortHash(req);
        return new RedirectView(data.targetUrl());        
    }
    
    @GetMapping("/")
    public RedirectView getRedirect() {
        return new RedirectView(CLIENT_BASE_URL);      
    }
}

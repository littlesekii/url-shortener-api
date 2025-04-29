package cat.linky.urlshortener_api.core.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import cat.linky.urlshortener_api.core.model.dto.UrlRefDTO;
import cat.linky.urlshortener_api.core.service.UrlRefService;


@RestController
@RequestMapping("/short")
public class MainController {

    private UrlRefService service;

    public MainController(UrlRefService service) {
        this.service = service;
    }
    
    @PostMapping
    public ResponseEntity<UrlRefDTO> post(@RequestBody UrlRefDTO req) {
        UrlRefDTO res = service.create(req);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(res.id())
            .toUri();

        return ResponseEntity.created(uri).body(res);
    }
    
}

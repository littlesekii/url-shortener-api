package cat.linky.urlshortener_api.core.controller;

import java.net.URI;
import java.net.http.HttpClient;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.servlet.view.RedirectView;

import cat.linky.urlshortener_api.core.model.dto.UrlRefDTO;
import cat.linky.urlshortener_api.core.service.UrlRefService;

@RestController
@RequestMapping("")
public class MainController {

    private UrlRefService service;

    public MainController(UrlRefService service) {
        this.service = service;
    }
    

    @PostMapping
    @RequestMapping("/api/short")
    public ResponseEntity<UrlRefDTO> post(@RequestBody UrlRefDTO req) {
        UrlRefDTO res;

        if(req.urlDest() == null) {
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
        UrlRefDTO data = service.findByUrlRef(req);

        return new RedirectView(data.urlDest());

        // if (res == null) {
        //     return ResponseEntity.notFound().build();
        // }

        // return ResponseEntity.ok().body(res);

        
    }
    @GetMapping("/")
    public RedirectView getRedirect() {
        return new RedirectView("https://shorturl.linky.cat");      
    }
}

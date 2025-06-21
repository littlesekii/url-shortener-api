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
import cat.linky.urlshortener_api.core.model.dto.ShortUrlReCaptchaTokenDTO;
import cat.linky.urlshortener_api.core.service.ReCaptchaService;
import cat.linky.urlshortener_api.core.service.ShortUrlInteractionService;
import cat.linky.urlshortener_api.core.service.ShortUrlService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping()
public class MainController {

    @Value("${client.url}")
    private String CLIENT_BASE_URL;

    private ShortUrlService service;
    private ShortUrlInteractionService interactionService;
    private ReCaptchaService reCaptchaService;

    public MainController(
        ShortUrlService service, 
        ShortUrlInteractionService interactionService,
        ReCaptchaService reCaptchaService
    ) {
        this.service = service;
        this.interactionService = interactionService;
        this.reCaptchaService = reCaptchaService;
    }
    
    @PostMapping("/api/shorten")
    public ResponseEntity<ShortUrlDTO> post(@RequestBody ShortUrlReCaptchaTokenDTO req, HttpServletRequest reqInfo) {
        ShortUrlDTO res;

        System.out.println(reqInfo.getRemoteAddr());
        System.out.println(reqInfo.getHeader("X-FORWARDED-FOR"));

        if (!reCaptchaService.verify(req.recaptchaToken())) {
            return ResponseEntity.badRequest().build();
        }

        String targetUrl = service.normalizeTargetUrl(req.targetUrl());
        if(targetUrl == null) {
            return ResponseEntity.badRequest().build();
        }

        res = service.create(new ShortUrlDTO(
            null, 
            "", 
            "", 
            targetUrl,
            null
        ));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(res.id())
            .toUri();

        return ResponseEntity.created(uri).body(res);
    }

    @GetMapping("/api/shorturl/{req}")
    public ResponseEntity<ShortUrlDTO> get(@PathVariable String req) {
        ShortUrlDTO res = service.findByShortHash(req);

        if (res.id() == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok().body(res);       
    }

    @GetMapping("/{req}")
    public RedirectView getRedirect(@PathVariable String req) {
        ShortUrlDTO data = service.findByShortHash(req);

        if (data.id() != null)
            interactionService.create(data.id());    
        
        return new RedirectView(data.targetUrl());        
    }
    
    @GetMapping("/")
    public RedirectView getRedirect() {
        return new RedirectView(CLIENT_BASE_URL);      
    }
}

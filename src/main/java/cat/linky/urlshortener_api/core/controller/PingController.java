package cat.linky.urlshortener_api.core.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/ping")
public class PingController {
    
    @GetMapping
    public String get() {
        return "Pong🏓";
    }
    
}

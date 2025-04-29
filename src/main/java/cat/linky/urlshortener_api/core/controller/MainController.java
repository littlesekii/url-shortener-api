package cat.linky.urlshortener_api.core.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/short")
public class MainController {
    
    @GetMapping
    public String get() {
        return "Hello API";
    }
    
}

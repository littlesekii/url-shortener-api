package cat.linky.urlshortener_api.core.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import cat.linky.urlshortener_api.core.model.dto.ReCaptchaDTO;

@FeignClient(name = "ReCaptcha", url = "https://www.google.com")
public interface ReCaptchaClient {

    @PostMapping(
        value = "/recaptcha/api/siteverify",
        consumes = "application/x-www-form-urlencoded" 
    )
    ReCaptchaDTO post(@RequestBody MultiValueMap<String, String> req);
    
}
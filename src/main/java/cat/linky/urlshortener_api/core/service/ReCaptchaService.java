package cat.linky.urlshortener_api.core.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import cat.linky.urlshortener_api.core.client.ReCaptchaClient;
import cat.linky.urlshortener_api.core.model.dto.ReCaptchaDTO;

@Service
public class ReCaptchaService {

    @Value("${recaptcha.secret-key}")
    private String reCaptchaSecretKey;
    
    private final ReCaptchaClient reCaptchaClient;

    public ReCaptchaService(ReCaptchaClient reCaptchaClient) {
        this.reCaptchaClient = reCaptchaClient;
    }

    public boolean verify(String token) {

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("secret", reCaptchaSecretKey);
        formData.add("response", token);

        ReCaptchaDTO res = reCaptchaClient.post(formData);

        System.out.println(res);
        if (!res.success() || res.score() < 0.5) {
            return false;
        }

        return true;
    }
}

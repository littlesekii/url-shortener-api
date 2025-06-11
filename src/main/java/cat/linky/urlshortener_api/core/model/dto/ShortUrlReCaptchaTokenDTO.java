package cat.linky.urlshortener_api.core.model.dto;

public record ShortUrlReCaptchaTokenDTO(String targetUrl, String recaptchaToken) {}

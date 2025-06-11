package cat.linky.urlshortener_api.core.model.dto;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ReCaptchaDTO(
    Boolean success, 
    Float score, 
    String action, 
    @JsonProperty("challenge_ts") Instant challengeTS, 
    String hostname, 
    @JsonProperty("error-codes") List<String> errorCodes
) {}

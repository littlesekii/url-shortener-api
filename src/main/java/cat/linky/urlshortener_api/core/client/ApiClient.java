package cat.linky.urlshortener_api.core.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
    name = "url-shortener-api", 
    url = "${server.url}"
)
public interface ApiClient {

    @GetMapping("/ping")
    String ping();
}

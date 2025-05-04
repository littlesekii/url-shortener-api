package cat.linky.urlshortener_api.process;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "https://sh.linky.cat", url = "https://sh.linky.cat")
public interface ApiClient {

    @RequestMapping(method = RequestMethod.GET, value = "/ping")
    String ping();
}

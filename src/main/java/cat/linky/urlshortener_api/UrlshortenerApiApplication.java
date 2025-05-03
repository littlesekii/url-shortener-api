package cat.linky.urlshortener_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UrlshortenerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrlshortenerApiApplication.class, args);
	}

}

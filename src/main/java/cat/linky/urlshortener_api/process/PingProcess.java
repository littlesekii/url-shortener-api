package cat.linky.urlshortener_api.process;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import cat.linky.urlshortener_api.core.client.ApiClient;

@SuppressWarnings("all")
@Component
public class PingProcess implements CommandLineRunner {

    private final ApiClient apiClient;

    public PingProcess(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    private Runnable ping = new Runnable() {
        @Override
        public void run() {
            while(true) {
                try {
                    System.out.println("API ping response: " + apiClient.ping());
                } catch (RuntimeException e) {
                    System.out.println("Failed while pinging.");
                }

                try {
                    Thread.sleep(1000 * 60 * 14);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    };

    @Override
    public void run(String... args) throws Exception {
        new Thread(ping).run();
    }
}
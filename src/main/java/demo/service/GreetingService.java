package demo.service;

import demo.model.Greeting;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GreetingService {

    private final RestTemplate restTemplate;

    public GreetingService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Greeting getGreeting() {
        return restTemplate.getForObject("http://localhost:8080/greeting", Greeting.class);
    }
}

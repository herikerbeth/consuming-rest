package demo.service;

import demo.TestData;
import demo.model.Greeting;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class GreetingServiceIT {

    @Autowired
    private GreetingService service;

    @MockitoBean
    private RestTemplate restTemplate;

    @Test
    void testGreeting() {
        Greeting greeting = TestData.newGreeting();
        when(restTemplate.getForObject("http://localhost:8080/greeting", Greeting.class))
                .thenReturn(greeting);

        Greeting actualGreeting = service.getGreeting();

        assertThat(actualGreeting).isNotNull();
        assertThat(actualGreeting.type()).isEqualTo(greeting.type());
        assertThat(actualGreeting.value()).isNotNull();
        assertThat(actualGreeting.value().id()).isEqualTo(greeting.value().id());
        assertThat(actualGreeting.value().content()).isEqualTo(greeting.value().content());
    }
}

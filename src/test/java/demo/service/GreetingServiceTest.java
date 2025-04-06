package demo.service;

import demo.TestData;
import demo.model.Greeting;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GreetingServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private GreetingService service;

    @Test
    void getGreeting_shouldReturnGreeting() {
        Greeting expectedGreeting = TestData.newGreeting();

        when(restTemplate.getForObject("http://localhost:8080/greeting", Greeting.class))
                .thenReturn(expectedGreeting);

        Greeting actualGreeting = service.getGreeting();

        assertThat(actualGreeting).isNotNull();
        assertThat(actualGreeting.type()).isEqualTo(expectedGreeting.type());
        assertThat(actualGreeting.value()).isNotNull();
        assertThat(actualGreeting.value().id()).isEqualTo(expectedGreeting.value().id());
        assertThat(actualGreeting.value().content()).isEqualTo(expectedGreeting.value().content());

        verify(restTemplate, times(1)).getForObject("http://localhost:8080/greeting", Greeting.class);
    }
}

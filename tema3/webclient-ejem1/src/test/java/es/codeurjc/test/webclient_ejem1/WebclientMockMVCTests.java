package es.codeurjc.test.webclient_ejem1;

import static org.mockito.Mockito.when;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;

@SpringBootTest
@AutoConfigureMockMvc
public class WebclientMockMVCTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BooksService booksService;

    private WebTestClient webTestClient;

    @BeforeEach
    public void setup() {
        this.webTestClient = MockMvcWebTestClient
            .bindTo(mockMvc)
            .build();

        when(booksService.getBookTitles("Java"))
            .thenReturn(List.of("Java SE 6","Java a Tope: J2me (java 2 Micro Edition).","Manual de Java","Java API FAQ"));
    }

    @Test
	public void MOCK_getJavaBooksTest() throws InterruptedException {
		this.webTestClient
			.get()
				.uri("/booktitles?title=Java")
			.exchange()
				.expectStatus()
					.isOk()
				.expectBody()
					.jsonPath("$[0]")
						.value(Matchers.containsString("Java"));
	}
    
}

package es.codeurjc.test.webclient_ejem1;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class WebclientRESTTests {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	public void REST_getJavaBooksTest() throws InterruptedException {
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

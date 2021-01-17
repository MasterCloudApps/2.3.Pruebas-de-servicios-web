package es.codeurjc.test.cdctesting_ejem1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@AutoConfigureStubRunner(ids={"es.codeurjc.test:cdctesting_ejem1_book:+:stubs:8080"},
	stubsMode = StubsMode.LOCAL)
class DemoApplicationTests {

	@Test
	void verify_book_service() {
		RestTemplate restTemplate = new RestTemplate();

		Book book = restTemplate.getForObject("http://localhost:8080/", Book.class);

		assertEquals("Cien años de soledad", book.title);
		assertEquals("Gabriel García Márquez", book.author);
	}

}

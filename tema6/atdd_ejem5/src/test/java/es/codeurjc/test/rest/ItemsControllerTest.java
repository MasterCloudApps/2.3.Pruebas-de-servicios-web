package es.codeurjc.test.rest;

import com.intuit.karate.junit5.Karate;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ItemsControllerTest {

    @LocalServerPort
    int port;

    @BeforeEach
    public void setPort(){
        System.setProperty("base.url", "http://localhost:" + port);
    }

    @Karate.Test
    public Karate testSample() {
        return Karate.run("items").relativeTo(getClass());
    }

}



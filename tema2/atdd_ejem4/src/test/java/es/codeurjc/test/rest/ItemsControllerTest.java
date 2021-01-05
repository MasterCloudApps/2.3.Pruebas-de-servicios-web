package es.codeurjc.test.rest;

import com.intuit.karate.junit5.Karate;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ItemsControllerTest {

    @Karate.Test
    public Karate testSample() {
        return Karate.run("items").relativeTo(getClass());
    }

}



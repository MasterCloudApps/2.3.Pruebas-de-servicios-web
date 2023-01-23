package es.codeurjc.anuncios;

import com.intuit.karate.junit5.Karate;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AnunciosControllerTest {

    @Karate.Test
    public Karate testSample() {
        return Karate.run("anuncios").relativeTo(getClass());
    }

}

package es.codeurjc.anuncios;

import org.springframework.boot.test.context.SpringBootTest;

import com.intuit.karate.junit5.Karate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AnunciosControllerTest {

    @Karate.Test
    public Karate testSample() {
        return Karate.run("anuncios").relativeTo(getClass());
    }

}

package es.codeurjc.anuncios;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

public class AnunciosControllerFindAllTest extends AbstractControllerTest{

    @DynamicPropertySource
    public static void overrideProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
    }

    @Test
    public void test() throws InterruptedException {
        List<Anuncio> anuncios = anunciosRepository.findAll();
        assertEquals(anuncios.size(), 2);
    }

}
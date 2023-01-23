package es.codeurjc.anuncios;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

public class AnunciosControllerDeleteAllTest extends AbstractControllerTest{

    @DynamicPropertySource
    public static void overrideProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
    }

    @Test
    public void test() throws InterruptedException {
        anunciosRepository.deleteAll();
        assertEquals(anunciosRepository.count(), 0);
    }

}

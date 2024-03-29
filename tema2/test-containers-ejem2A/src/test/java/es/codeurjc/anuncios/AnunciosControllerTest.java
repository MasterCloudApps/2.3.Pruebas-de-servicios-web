package es.codeurjc.anuncios;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
public class AnunciosControllerTest{
	
	@Container
    public static MySQLContainer mySQLContainer = new MySQLContainer<>("mysql:8.0")
      .withDatabaseName("test")
      .withUsername("user")
      .withPassword("pass");
	
    @DynamicPropertySource
    public static void overrideProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
    }

    @Autowired
    private AnunciosRepository anunciosRepository;

    @Test
    public void test() throws InterruptedException {
        List<Anuncio> anuncios = anunciosRepository.findAll();
        assertEquals(anuncios.size(), 2);
    }

}
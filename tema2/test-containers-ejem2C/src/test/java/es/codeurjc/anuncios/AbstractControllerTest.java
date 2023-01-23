package es.codeurjc.anuncios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
public class AbstractControllerTest {

    @Autowired
    protected AnunciosRepository anunciosRepository;

    @Container
    public static MySQLContainer mySQLContainer = new MySQLContainer<>("mysql:8.0")
      .withDatabaseName("test")
      .withUsername("user")
      .withPassword("pass");
    
}

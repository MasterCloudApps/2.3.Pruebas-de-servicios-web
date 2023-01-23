package es.codeurjc.anuncios;

import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

// @SpringBootTest
@Testcontainers
public abstract class AbstractDatabaseTest {
	
    @Container
    public static MySQLContainer mySQLContainer = new MySQLContainer<>("mysql:8.0.24")
      .withDatabaseName("test")
      .withUsername("user")
      .withPassword("pass")
      .withReuse(true);

}

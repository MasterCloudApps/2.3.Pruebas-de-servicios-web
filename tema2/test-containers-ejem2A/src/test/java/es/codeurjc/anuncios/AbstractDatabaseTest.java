package es.codeurjc.anuncios;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
@ContextConfiguration(initializers = AbstractDatabaseTest.Initializer.class)
public abstract class AbstractDatabaseTest {
	
	@Container
    public static MySQLContainer mySQLContainer = new MySQLContainer("mysql:5.7")
      .withDatabaseName("test")
      .withUsername("user")
      .withPassword("pass");
	
	static class Initializer
    implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
              "spring.datasource.url=" + mySQLContainer.getJdbcUrl()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

}

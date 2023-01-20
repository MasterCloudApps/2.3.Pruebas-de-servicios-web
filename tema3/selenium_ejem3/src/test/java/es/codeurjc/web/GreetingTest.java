package es.codeurjc.web;

import static org.assertj.core.api.Assertions.assertThat;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class GreetingTest {

	@ConfigProperty(name = "quarkus.http.test-port")
	int httpTestPort;

	private WebDriver driver;

	@BeforeAll
	public static void setupClass() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void setupTest() {
		driver = new ChromeDriver();
	}

	@AfterEach
	public void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test
	public void greetingTest() {

		String name = "Michel";

		driver.get("http://localhost:"+httpTestPort+"/greeting?name=" + name);

		String greeting = driver.findElement(By.id("greeting")).getText();

		assertThat("Hello, "+name).isEqualTo(greeting);
	}
}

package es.codeurjc.test.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testcontainers.Testcontainers.exposeHostPorts;
import static org.testcontainers.containers.BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.containers.VncRecordingContainer.VncRecordingFormat;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


@Testcontainers
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebAppTest {

	@LocalServerPort
    int port;

	@Container
    public static BrowserWebDriverContainer<?> seleniumContainer = new BrowserWebDriverContainer<>()
		.withAccessToHost(true)
		.withRecordingMode(RECORD_ALL, new File("target"), VncRecordingFormat.MP4);

	private RemoteWebDriver driver;

	@BeforeEach
	public void setupTest() {
		exposeHostPorts(port);
		driver = new RemoteWebDriver(seleniumContainer.getSeleniumAddress(), new ChromeOptions());
	}

	@AfterEach
	public void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test
	public void test() throws InterruptedException {
		
		driver.get("http://host.testcontainers.internal:"+this.port+"/");

		String newTitle = "MessageTitle";
		String newBody = "MessageBody";

		Thread.sleep(1000);		
		
		driver.findElement(By.id("title-input")).sendKeys(newTitle);
		driver.findElement(By.id("body-input")).sendKeys(newBody);

		Thread.sleep(1000);
		
		driver.findElement(By.id("submit")).click();

		Thread.sleep(1000);
		
		String title = driver.findElement(By.id("title")).getText();
		String body = driver.findElement(By.id("body")).getText();

		assertThat(title).isEqualTo(newTitle);
		assertThat(body).isEqualTo(newBody);

	}

}

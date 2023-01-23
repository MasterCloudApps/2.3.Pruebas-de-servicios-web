package es.codeurjc.test.web;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testcontainers.containers.BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL;
import org.testcontainers.containers.VncRecordingContainer.VncRecordingFormat;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class WikipediaTest {

	@Container
    public static BrowserWebDriverContainer<?> seleniumContainer = new BrowserWebDriverContainer<>()
		.withRecordingMode(RECORD_ALL, new File("target"), VncRecordingFormat.MP4);

	private RemoteWebDriver driver;

	@BeforeEach
	public void setupTest() {
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
		
		driver.get("https://wikipedia.org");
        WebElement searchInput = driver.findElement(By.name("search"));

        Thread.sleep(2000);
        
        searchInput.sendKeys("Rick Astley");
        searchInput.submit();

        Thread.sleep(2000);
        
        WebElement link = driver.findElement(By.linkText("Rickrolling"));
        link.click();
        
        Thread.sleep(2000);

        boolean memeFound = driver.findElements(By.cssSelector("p"))
                .stream()
                .anyMatch(element -> element.getText().contains("meme"));

        assertTrue(memeFound, "Rickrolling page should contain meme word");
	}

}








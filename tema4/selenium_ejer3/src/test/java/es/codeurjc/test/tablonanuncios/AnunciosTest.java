package es.codeurjc.test.tablonanuncios;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import es.codeurjc.test.tablonanuncios.page.AnuncioPage;
import es.codeurjc.test.tablonanuncios.page.MensajePage;
import es.codeurjc.test.tablonanuncios.page.NuevoAnuncioPage;
import es.codeurjc.test.tablonanuncios.page.TablonPage;
import io.github.bonigarcia.wdm.WebDriverManager;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AnunciosTest {

	@LocalServerPort
    private int port;

	private WebDriver driver;

	private String userName = "User_" + Double.toString(Math.random());

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
	public void createAnuncio() {

		TablonPage tablon = new TablonPage(driver, "http://localhost:"+port);

		NuevoAnuncioPage nuevoAnuncio = tablon.get().nuevoAnuncio();		
		
		MensajePage creado = nuevoAnuncio.crear(userName, "Subject", "Comment");
		
		tablon = creado.volverAlTablon();
		
		assertTrue(tablon.estaAnuncio(userName, "Subject"));
		
	}
	
	@Test
	public void borrarAnuncio() {
		
		TablonPage tablon = new TablonPage(driver, "http://localhost:"+port);

		NuevoAnuncioPage nuevoAnuncio = tablon.get().nuevoAnuncio();		
		
		MensajePage creado = nuevoAnuncio.crear(userName, "Subject", "Comment");
		
		tablon = creado.volverAlTablon();
		
		AnuncioPage anuncio = tablon.get().verAnuncio(userName, "Subject");
		
		MensajePage borrado = anuncio.borrar();
		
		tablon = borrado.volverAlTablon();
		
		assertFalse(tablon.estaAnuncio(userName, "Subject"));
	}
	
}

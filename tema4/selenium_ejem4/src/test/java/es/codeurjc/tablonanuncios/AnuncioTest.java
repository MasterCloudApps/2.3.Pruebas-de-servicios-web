package es.codeurjc.tablonanuncios;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.web.server.LocalServerPort;

import es.codeurjc.tablonanuncios.pages.TablonPage;
import es.codeurjc.test.tablonanuncios.Application;

import org.springframework.boot.test.context.SpringBootTest;

import io.github.bonigarcia.wdm.WebDriverManager;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AnuncioTest {

	@LocalServerPort
    int port;

	WebDriver driver;
	
	@BeforeAll
	public static void setupClass() {
		WebDriverManager.chromedriver().setup();
	}
	
	@BeforeEach
	public void setup() {
		driver = new ChromeDriver();
	}
	
	@AfterEach
	public void teardown() {
		if(driver != null) {
			driver.quit();
		}
	}
	
	@Test
	public void createAnuncio() throws InterruptedException {
		TablonPage tablon = new TablonPage(driver, port);
		
		String name = "Michel";
		String asunto = "Vendo coche";
		String comentario = "Vendo Opel Corsa";
		
		tablon.get() // -> Go to TablonPage
			.nuevoAnuncio() // -> Go to AnuncioFormPage
				.rellenarNuevoAnuncio(name, asunto, comentario)
			.enviarNuevoAnuncio() // -> Go to AnuncioResult
			.volverAlTablon() // -> Go to TablonPage
				.comprobarNuevoAnuncio(name, asunto);
	}
	
	@Test
	public void deleteAnuncio() throws InterruptedException {
		TablonPage tablon = new TablonPage(driver, port);
		
		String nombre = "Pepe";
		String asunto = "Hola caracola";		
		
		tablon.get() // -> Go to TablonPage
			.verAnuncio(nombre, asunto) // -> Go to AnuncioPage
				.borrarAnuncio() // -> Go to AnuncioResult
			.volverAlTablon() // -> Go to TablonPage
				.comprobarAnuncioBorrado(nombre, asunto);
	}

}

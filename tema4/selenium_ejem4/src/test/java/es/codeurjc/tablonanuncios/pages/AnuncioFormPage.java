package es.codeurjc.tablonanuncios.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AnuncioFormPage extends Page{
	
	public AnuncioFormPage(WebDriver driver, int port) {
        super(driver, port);
    }
	
	public AnuncioFormPage(Page page) {
        super(page);
    }
	
	public AnuncioFormPage get(){
    	this.get("/nuevo_anuncio");
    	wait.until(ExpectedConditions.elementToBeClickable(By.tagName("h1")));
    	return this;
    }
	
	public AnuncioFormPage rellenarNuevoAnuncio(String nombre, String asunto, String comentario) {
    	driver.findElement(By.name("nombre")).sendKeys(nombre);
		driver.findElement(By.name("asunto")).sendKeys(asunto);
		driver.findElement(By.name("comentario")).sendKeys(comentario);
        return this;
    }
    
    public AnuncioResult enviarNuevoAnuncio() {
    	driver.findElement(By.xpath("//input[@type='submit']")).click();
        return new AnuncioResult(this);
    }

}

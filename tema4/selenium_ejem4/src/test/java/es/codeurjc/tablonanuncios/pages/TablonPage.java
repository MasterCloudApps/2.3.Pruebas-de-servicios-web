package es.codeurjc.tablonanuncios.pages;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TablonPage extends Page{
 
    public TablonPage(WebDriver driver, int port) {
        super(driver, port);
    }
    
    public TablonPage(Page page) {
        super(page);
    }
    
    public TablonPage get(){
    	this.get("/");
    	wait.until(ExpectedConditions.elementToBeClickable(By.tagName("h1")));
    	return this;
    }
    
    public AnuncioFormPage nuevoAnuncio() {
    	findElementWithText("Nuevo anuncio").click();
        return new AnuncioFormPage(this);
    }
    
    public AnuncioPage verAnuncio(String nombre, String asunto) {
    	getAnuncioElem(nombre, asunto).click();
        return new AnuncioPage(this);
    }
    
    public TablonPage comprobarNuevoAnuncio(String nombre, String asunto) {
    	assertNotNull(getAnuncioElem(nombre, asunto));
        return this;
    }
    
    public TablonPage comprobarAnuncioBorrado(String nombre, String asunto) {
    	assertFalse(isElementPresent(nombre+" "+asunto));
        return this;
    }
    
    private WebElement getAnuncioElem(String nombre, String asunto) {
    	return findElementWithText(nombre+" "+asunto);
    }

}

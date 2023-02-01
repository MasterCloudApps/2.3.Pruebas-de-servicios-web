package es.codeurjc.tablonanuncios.pages;

import static org.junit.Assert.assertNotNull;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AnuncioPage extends Page{
 
    public AnuncioPage(WebDriver driver, int port) {
        super(driver, port);
    }
    
    public AnuncioPage(Page page) {
        super(page);
    }
    
    public AnuncioPage get(Long id){
    	this.get("/anuncio/"+id);
    	wait.until(ExpectedConditions.elementToBeClickable(By.tagName("p")));
    	return this;
    }
    
    public AnuncioPage comprobarAnuncio(String nombre, String asunto, String comentario) {
    	assertNotNull(findElementWithText(nombre));
    	assertNotNull(findElementWithText(asunto));
    	assertNotNull(findElementWithText(comentario));
        return this;
    }
    
    public AnuncioResult borrarAnuncio() {
    	findElementWithText("Borrar anuncio").click();
        return new AnuncioResult(this);
    }
    
    public TablonPage volverAlTablon() {
    	findElementWithText("Volver al tablón").click();
        return new TablonPage(this);
    }
    

}

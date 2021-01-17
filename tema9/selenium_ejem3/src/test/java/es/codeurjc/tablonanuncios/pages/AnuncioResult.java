package es.codeurjc.tablonanuncios.pages;

import org.openqa.selenium.WebDriver;

public class AnuncioResult extends Page{
	
	public AnuncioResult(WebDriver driver, int port) {
        super(driver, port);
    }
	
	public AnuncioResult(Page page) {
        super(page);
    }
	
    public TablonPage volverAlTablon() {
    	findElementWithText("Volver al tablón").click();
        return new TablonPage(this);
    }
}

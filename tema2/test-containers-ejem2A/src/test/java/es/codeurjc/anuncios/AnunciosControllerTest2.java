package es.codeurjc.anuncios;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AnunciosControllerTest2 extends AbstractDatabaseTest{
	
	@Autowired
    private AnunciosRepository anunciosRepository;

    @Test
    public void test() throws InterruptedException {
    	anunciosRepository.deleteAll();
        assertEquals(anunciosRepository.count(), 0);
    }

}

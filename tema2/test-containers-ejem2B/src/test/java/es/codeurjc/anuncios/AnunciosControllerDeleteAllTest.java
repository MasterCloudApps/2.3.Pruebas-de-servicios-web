package es.codeurjc.anuncios;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class AnunciosControllerDeleteAllTest{
	
	@Autowired
    private AnunciosRepository anunciosRepository;

    @Test
    public void test() throws InterruptedException {
    	anunciosRepository.deleteAll();
        assertEquals(anunciosRepository.count(), 0);
    }

}

package es.codeurjc.anuncios;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;


public class AnunciosControllerTest extends AbstractDatabaseTest{

    @Autowired
    private AnunciosRepository anunciosRepository;

    @Test
    public void test() throws InterruptedException {
        List<Anuncio> anuncios = anunciosRepository.findAll();
        assertEquals(anuncios.size(), 2);
    }

}
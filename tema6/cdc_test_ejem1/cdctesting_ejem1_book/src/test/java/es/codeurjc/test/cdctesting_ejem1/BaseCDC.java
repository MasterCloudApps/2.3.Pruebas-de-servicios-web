package es.codeurjc.test.cdctesting_ejem1;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.codeurjc.test.cdctesting_ejem1.BookController;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

@SpringBootTest
public abstract class BaseCDC {

    @Autowired
    BookController bookController;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(bookController);
    }
}
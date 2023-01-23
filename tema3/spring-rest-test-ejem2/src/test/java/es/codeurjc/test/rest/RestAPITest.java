package es.codeurjc.test.rest;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestAPITest {
	
	@LocalServerPort
    int port;
	
	@BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }
    
	@Test
	public void itemAddTest() {
		
		given().
			contentType("application/json").
			body("{\"description\":\"milk\",\"checked\":false }").
		when().
			post("/items/").
		then().
			statusCode(201).
			body("description", equalTo("milk")).
			body("checked", equalTo(false));
	}
    
}



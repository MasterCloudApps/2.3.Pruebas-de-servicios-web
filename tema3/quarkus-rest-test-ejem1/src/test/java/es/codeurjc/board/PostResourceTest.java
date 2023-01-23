package es.codeurjc.board;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;

import static org.hamcrest.CoreMatchers.*;

@QuarkusTest
@TestHTTPEndpoint(PostResource.class)
public class PostResourceTest {

    @Test
    public void testCreatePost() {
    	
        String body = "{\"user\":\"Michel\",\"title\":\"Vendo Opel Corsa\",\"text\":\"Bueno, bonito y barato\"}";

        given().
            contentType("application/json").
            body(body).
        when().
            post().
        then().
            statusCode(201).
            body("user", equalTo("Michel")).
            body("title", equalTo("Vendo Opel Corsa")).
            body("text", equalTo("Bueno, bonito y barato"));
    }
    
}

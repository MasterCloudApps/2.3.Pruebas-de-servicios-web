package es.codeurjc.board;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.junit.jupiter.api.Test;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class PostResourceTest {
    
	@Inject PostResource postResource;

	@Test
	public void getPostTest() throws Exception {

        Response response = postResource.getPost();

        Post recivedPost = (Post) response.getEntity();

        assertEquals("Pepe", recivedPost.getUser());
		
	}
    
}

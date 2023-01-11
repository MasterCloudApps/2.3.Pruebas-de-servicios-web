package es.codeurjc.board;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;

@QuarkusTest
public class PostResourceUnitTest {

    @InjectMock PostRepository postRepository;
    
    @Inject PostResource postResource;

    @Test
	public void getPostTest() throws Exception {

        Post fakePost = new Post("Pepe", "Vendo moto", "Barata, barata");
		
	    Mockito.when(postRepository.findById(1L)).thenReturn(fakePost);

	    Post recivedPost = postResource.getPost(1L);

        Mockito.verify(postRepository).findById(1L);

        assertEquals(fakePost.getTitle(), recivedPost.getTitle());
        assertEquals(fakePost.getUsername(), recivedPost.getUsername());
        assertEquals(fakePost.getText(), recivedPost.getText());
		
	}
    
}

package es.codeurjc.board;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class PostResourceITest {
    
    @Inject PostResource postResource;

    @Test
    public void createPostTest() throws Exception {

        // GIVEN
        Post newPost = new Post("Pepe", "Vendo moto", "Barata, barata");
        
        // WHEN 
        Post createdPost = (Post) postResource.createPost(newPost);

        // THEN -> Check return same Post
        assertEquals(newPost.getTitle(), createdPost.getTitle());
        assertEquals(newPost.getUsername(), createdPost.getUsername());
        assertEquals(newPost.getText(), createdPost.getText());

        // THEN -> Check that Post persisted
        Post recivedPost = postResource.getPost(createdPost.getId());
        assertEquals(newPost.getTitle(), recivedPost.getTitle());
        assertEquals(newPost.getUsername(), recivedPost.getUsername());
        assertEquals(newPost.getText(), recivedPost.getText());

    }
    
}

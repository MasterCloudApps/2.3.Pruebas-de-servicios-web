package es.codeurjc.board;

import java.util.Collection;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import io.smallrye.common.annotation.Blocking;

@Path("/posts")
@Blocking
public class PostResource {

	@Inject
	PostRepository posts;

	@GET
	public Collection<Post> getPosts(@QueryParam("username") String username) {

		if(username == null){
			return posts.listAll();
		} else {
			return posts.findByUsername(username);
		}
	}

	@GET
	@Path("{id}")
	public Post getPost(@PathParam("id") long id) {

		return posts.findById(id);
	}

	@POST
	@Transactional
	public Post createPost(Post post) {

		posts.persist(post);

		return post;
	}

	@PUT
	@Path("{id}")
	@Transactional
	public Post replacePost(@PathParam("id") long id, Post newPost) {

		posts.findByIdOptional(id).orElseThrow();

		newPost.setId(id);

		posts.getEntityManager().merge(newPost);

		return newPost;
	}

	@DELETE
	@Path("{id}")
	@Transactional
	public Post deletePost(@PathParam("id") long id) {

		Post post = posts.findByIdOptional(id).orElseThrow();

		posts.deleteById(id);

		return post;
	}
}
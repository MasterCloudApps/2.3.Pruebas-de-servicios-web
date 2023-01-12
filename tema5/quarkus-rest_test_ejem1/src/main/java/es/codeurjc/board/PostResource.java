package es.codeurjc.board;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/posts")
public class PostResource {

	@Inject
	private PostService posts;

	@GET
	public Collection<Post> getPosts() {
		return posts.findAll();
	}

	@GET
	@Path("{id}")
	public Response getPost(@PathParam("id") long id) {

		Post post = posts.findById(id);

		if (post != null) {
			return Response.ok(post).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@POST
	public Response createPost(Post post) {
		Post savedPost = posts.save(post);
		return Response.status(Response.Status.CREATED).entity(savedPost).build();
	}

	@PUT
	@Path("{id}")
	public Response replacePost(@PathParam("id") long id, Post newPost) {

		Post post = posts.findById(id);

		if (post != null) {

			newPost.setId(id);
			posts.save(newPost);

			return Response.ok(newPost).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@DELETE
	@Path("{id}")
	public Response deletePost(@PathParam("id") long id) {

		Post post = posts.findById(id);

		if (post != null) {
			posts.deleteById(id);
			return Response.ok(post).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
}

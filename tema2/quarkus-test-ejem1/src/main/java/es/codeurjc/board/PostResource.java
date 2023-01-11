package es.codeurjc.board;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/post")
public class PostResource {

	@GET
	@Path("")
	public Response getPost() {
		return Response.ok(new Post("Pepe", "Vendo moto", "Barata, barata")).build();
	}

}

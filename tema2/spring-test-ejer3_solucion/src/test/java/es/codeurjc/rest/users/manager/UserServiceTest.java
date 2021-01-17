package es.codeurjc.rest.users.manager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import es.codeurjc.rest.users.User;

@RestClientTest(UserService.class)
public class UserServiceTest {

	@Autowired
	private UserService userService;

	@Autowired
	private MockRestServiceServer userServer;

	String res1 = "[{ \"id\": 1, \"name\": \"Juan\", \"email\": \"juan@urjc.es\", \"editable\": false }]";
	String res2 = "{ \"id\": 1,  \"name\": \"Juan\", \"email\": \"juan@urjc.es\", \"editable\": false }";


	@Test
	public void getUsersTest() throws Exception {

		this.userServer.expect(requestTo(UserService.BASE_URL))
				.andRespond(withSuccess(res1, MediaType.APPLICATION_JSON));

		User[] users = this.userService.getUsers();

		assertThat(users).hasSize(1);
	}

	@Test
	public void getOneUserTest() throws Exception {

		this.userServer.expect(requestTo(UserService.BASE_URL+1))
				.andRespond(withSuccess(res2, MediaType.APPLICATION_JSON));

		User user = this.userService.getUser(1);

		assertThat(user.getName()).isEqualTo("Juan");
	}

	@Test
	public void postInvalidUserTest() throws Exception {

		User user = new User();
		user.setEditable(false);
		user.setEmail("invalidEmail");
		user.setName("Juan");

		// No llegará a realizar la peticion
		this.userServer.expect(requestTo(UserService.BASE_URL+1))
				.andRespond(withSuccess(res2, MediaType.APPLICATION_JSON));

		User postUser = this.userService.postUser(user);

		assertThat(postUser).isNull();
	}
}
package es.codeurjc.rest.users;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class UsersControllerTest {

	@Autowired
    private MockMvc mvc;
	
	@MockBean
	private UsersService usersService;
	
	@Test
	public void getUsersTest() throws Exception {
		 
	    List<User> users = Arrays.asList(new User("John"), new User("Peter"));
	 
	    when(usersService.getUsers()).thenReturn(users);
		
	    mvc.perform(get("/users/")
	    	      .contentType(MediaType.APPLICATION_JSON))
	    	      .andExpect(status().isOk())
	    	      .andExpect(jsonPath("$", hasSize(2)))
	    	      .andExpect(jsonPath("$[0].name", equalTo("John")));
		
	}
}

package es.codeurjc.rest.users.manager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;

import es.codeurjc.rest.users.User;

@Service
public class UserService{

    private RestTemplate restTemplate;

    public static final String BASE_URL = "http://localhost:9000/users/";

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

    public UserService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public User[] getUsers(){
        ResponseEntity<User[]> usersResponse = restTemplate.getForEntity(BASE_URL, User[].class);
        return usersResponse.getBody(); 
    }

    public User getUser(long id){
        ResponseEntity<User> userResponse = restTemplate.getForEntity(BASE_URL + id, User.class);
        return userResponse.getBody();
    }

    public User postUser(User user){
        if (validEmail(user.getEmail())) {

			RestTemplate restTemplate = new RestTemplate();

			ResponseEntity<User> userResponse = restTemplate.postForEntity(BASE_URL, user, User.class);

			return userResponse.getBody();

		} else {
			return null;
		}
    }

    public User putUser(long id, User user){
        ResponseEntity<User> updatedUserResponse = 
						restTemplate.exchange(BASE_URL + id, HttpMethod.PUT,
                        new HttpEntity<User>(user), User.class);
        return updatedUserResponse.getBody();
    }

    public static boolean validEmail(String emailStr) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		return matcher.find();
	}

}
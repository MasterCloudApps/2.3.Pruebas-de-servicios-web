package es.codeurjc.rest.users.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.rest.users.User;

@RestController
@RequestMapping("/manager/users")
public class UsersManagerController {

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public ResponseEntity<User[]> users() {
		return new ResponseEntity<User[]>(userService.getUsers(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable long id) {

		User user = userService.getUser(id);

		if(user != null){
			return new ResponseEntity<>(user, HttpStatus.OK);
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/")
	public ResponseEntity<User> createUser(@RequestBody User user) {

		User userCreated = userService.postUser(user);

		if (userCreated != null) {
			return new ResponseEntity<>(userCreated, HttpStatus.CREATED);

		} else {
			return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User user) {


		User checkUser = userService.getUser(id);

		if (checkUser == null) {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} else {

			if (checkUser.isEditable()) {

				User updatedUser = userService.putUser(id, user);

				return new ResponseEntity<>(updatedUser, HttpStatus.OK);
				
			} else {
				return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
			}
		}
	}

}

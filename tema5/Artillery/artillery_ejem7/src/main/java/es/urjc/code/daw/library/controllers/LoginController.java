package es.urjc.code.daw.library.controllers;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * This class is used to provide REST endpoints to logIn and logOut to the
 * service. These endpoints are used by Angular 2 SPA client application.
 * 
 * NOTE: This class is not intended to be modified by app developer.
 */
@RestController
@RequestMapping("/api/auth")
public class LoginController {

	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	@GetMapping("/logIn")
	public ResponseEntity<String> logIn(HttpServletRequest request) {

		Principal principal = request.getUserPrincipal();

		if (principal == null) {
			log.info("Not user logged");
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} else {
			log.info("Logged as " + principal.getName());
			return new ResponseEntity<>(principal.getName(), HttpStatus.OK);
		}
	}

	@GetMapping("/logOut")
	public ResponseEntity<String> logOut(HttpServletRequest request, HttpSession session) {

		Principal principal = request.getUserPrincipal();

		if (principal == null) {
			log.info("No user logged");
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} else {
			session.invalidate();
			log.info("Logged out");
			return new ResponseEntity<>("Logged out", HttpStatus.OK);
		}
	}

}
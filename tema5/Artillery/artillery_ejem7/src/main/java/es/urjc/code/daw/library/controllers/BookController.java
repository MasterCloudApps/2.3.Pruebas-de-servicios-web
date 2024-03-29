package es.urjc.code.daw.library.controllers;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.urjc.code.daw.library.models.Book;
import es.urjc.code.daw.library.repository.BookRepository;

@RestController
@RequestMapping("/api/books")
public class BookController {

	private static final Logger log = LoggerFactory.getLogger(BookController.class);

	@Autowired
	private BookRepository repository;

	@GetMapping("/")
	public Collection<Book> getBooks() {
		return repository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Book> getBook(@PathVariable long id) {

		log.info("Get book {}", id);
		
		if (repository.existsById(id)) {
			Book book = repository.findById(id).get();
			return new ResponseEntity<>(book, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Book> createBook(@RequestBody Book book) {
		
		Book savedBook = repository.save(book);
		
		return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Book> updateBook(@PathVariable long id, @RequestBody Book updatedBook) {
		
		if (repository.existsById(id)) {
			updatedBook.setId(id);
			repository.save(updatedBook);
			return new ResponseEntity<>(updatedBook, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Book> deleteBook(@PathVariable long id) {

		if (repository.existsById(id)) {
			repository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}

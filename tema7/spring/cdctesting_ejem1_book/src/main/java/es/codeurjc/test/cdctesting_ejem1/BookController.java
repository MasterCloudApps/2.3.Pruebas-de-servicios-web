package es.codeurjc.test.cdctesting_ejem1;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @PostConstruct
    public void init() {
        bookRepository.save(new Book("Cien años de soledad", "Gabriel García Márquez"));
    }

    @GetMapping(value = "/")
    public List<Book> getMethodName() {
        return bookRepository.findAll();
    }
    
}
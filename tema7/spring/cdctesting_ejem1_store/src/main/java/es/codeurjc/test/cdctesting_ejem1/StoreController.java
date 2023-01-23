package es.codeurjc.test.cdctesting_ejem1;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class StoreController {

    RestTemplate bookService;

    public StoreController(RestTemplateBuilder restTemplateBuilder) {
        this.bookService = restTemplateBuilder.build();
    }

    @GetMapping(value = "/")
    public List<Item> getMethodName(@RequestParam String search) {
        Book[] books = bookService.getForObject("http://localhost:8080/", Book[].class, search);
        List<Item> items = new ArrayList<>();
        for(Book b : books) {
            items.add(new Item(b.getTitle()));
        }
        return items;
    }
    
}
package es.urjc.code.daw.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.urjc.code.daw.library.models.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
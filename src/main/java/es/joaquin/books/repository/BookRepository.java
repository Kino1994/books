package es.joaquin.books.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.joaquin.books.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}

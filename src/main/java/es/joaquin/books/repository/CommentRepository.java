package es.joaquin.books.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.joaquin.books.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}

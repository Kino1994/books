package es.joaquin.books.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.joaquin.books.entities.Comment;
import es.joaquin.books.entities.User;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	List<Comment> findAllByUser(User user);

}

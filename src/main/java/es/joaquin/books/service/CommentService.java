package es.joaquin.books.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.joaquin.books.entities.Book;
import es.joaquin.books.entities.Comment;
import es.joaquin.books.entities.User;
import es.joaquin.books.repository.BookRepository;
import es.joaquin.books.repository.CommentRepository;
import es.joaquin.books.repository.UserRepository;

@Service
public class CommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private UserRepository userRepository;
		
	@PostConstruct
	public void init () {		
		List<Book> books = bookRepository.findAll();
		List<User> users = userRepository.findAll();
		Integer i = 1;
		for (User u: users) {
			for (Book b: books) {
				commentRepository.save(new Comment(null,"Comentario del usuario " + u.getNick() + " en el libro " + b.getId() + " con numero "+ i, i,b,u));
				if (i == 5) {
					i = 0;
				}
				i++;
			}
		}
		
	}	

}

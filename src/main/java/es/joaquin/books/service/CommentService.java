package es.joaquin.books.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.joaquin.books.entities.Book;
import es.joaquin.books.entities.Comment;
import es.joaquin.books.entities.User;
import es.joaquin.books.model.api.dto.CommentDTO;
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
	
	private static final ModelMapper modelMapper = new ModelMapper();
	
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
	
	public List<CommentDTO> findCommentsByUserId(Long userId){
		Optional<User> user = userRepository.findById(userId);
		
		if (!user.isEmpty() && user.isPresent()) {
			List<Comment> comments = commentRepository.findAllByUser(user.get());
			return comments.stream().map(comment -> 
				modelMapper.map(comment, CommentDTO.class).setBookId(comment.getBook().getId()))
				.collect(Collectors.toList());
		}
		return Collections.emptyList();
		
	};
	

}

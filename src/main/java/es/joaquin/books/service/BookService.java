package es.joaquin.books.service;

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
import es.joaquin.books.model.api.dto.BookDTO;
import es.joaquin.books.model.api.dto.CommentDTO;
import es.joaquin.books.repository.BookRepository;
import es.joaquin.books.repository.CommentRepository;
import es.joaquin.books.repository.UserRepository;

@Service
public class BookService {		
	
	@Autowired
	private BookRepository bookRepository;
		
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	private static final ModelMapper modelMapper = new ModelMapper();
	
	@PostConstruct
	public void init () {		
		bookRepository.save(new Book(null,"Atlas shrugged", "summary 1", "Ayn Rand", "Deusto", 2019,null));
		bookRepository.save(new Book(null,"Harry Potter y la camara secreta", "summary 2", "J.K Rowling", "Salamandra", 1999,null));
		bookRepository.save(new Book(null,"Contra la Teoría Monetaria Moderna", "summary 3", "Juan Ramon Rallo", "Deusto", 2017,null));
		bookRepository.save(new Book(null,"Estados Fallidos", "summary 4", "Noam Chomsky", "B de Bolsillo", 2017,null));
		bookRepository.save(new Book(null,"Viaje a la libertad economica", "summary 5", "Daniel Lacalle", "Deusto", 2013,null));
		bookRepository.save(new Book(null,"Memoria del comunismo", "summary 6", "Federico Jimenez Los Santos", "Espasa", 2018,null));
		bookRepository.save(new Book(null,"Liberalismo: Los 10 principios basicos del orden liberal", "summary 7", "Juan Ramon Rallo", "Deusto", 2019,null));
		bookRepository.save(new Book(null,"Padre Rico, Padre pobre", "summary 8", "Rober T Kiyoaski", "De Bolsillo", 2020,null));
		bookRepository.save(new Book(null,"Los años de Aznar", "summary 9", "Sergio Gomez Alba", "Cordoba", 2020,null));
		bookRepository.save(new Book(null,"Los ricos de Franco", "summary 10", "Mariano Sanchez Soler", "Roca", 2020,null));
		bookRepository.save(new Book(null,"Blockchain: La revolución industrial de Internet", "summary 11", "Alexander Preukschat", "Ediciones Gestion", 2017,null));
	}
	
	public List<BookDTO> findAll(){
		return bookRepository.findAll().stream().map(book -> modelMapper.map(book,  BookDTO.class)).collect(Collectors.toList());
	}
	
	public Optional<BookDTO> findById(Long id){
		Optional<Book> book = bookRepository.findById(id);
		if (!book.isEmpty() &&  book.isPresent()) {
			return Optional.of(modelMapper.map(bookRepository.save(book.get()), BookDTO.class));		
		}
		return Optional.empty();
	}
	
	public BookDTO save(BookDTO bookDTO) {
		Book book = modelMapper.map(bookDTO, Book.class);
		return modelMapper.map(bookRepository.save(book),BookDTO.class); 
	}
	
	public Optional<BookDTO> comment(Long bookId, CommentDTO commentDTO) {
		Optional<Book> book = bookRepository.findById(bookId);
		if (!book.isEmpty() && book.isPresent()) {
			Optional<User> user = userRepository.findByNick(commentDTO.getNick());
			if (!user.isEmpty() && user.isPresent()) {			
				Comment comment = modelMapper.map(commentDTO, Comment.class);
				comment.setUser(user.get());
				comment.setBook(book.get());
				commentRepository.save(comment);
				return Optional.of(modelMapper.map(bookRepository.findById(bookId).get(), BookDTO.class));
			}
		}
		return Optional.empty();
	}
	
	public Boolean delete(Long id) {
		Optional<Comment> comments = commentRepository.findById(id);
		if (!comments.isEmpty() && comments.isPresent()) {
			commentRepository.delete(comments.get());
			return true;
		}
		return false;
	}

}


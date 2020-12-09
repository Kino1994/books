package es.joaquin.books.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import es.joaquin.books.model.Book;
import es.joaquin.books.model.Comment;
import es.joaquin.books.model.api.BookPost;
import es.joaquin.books.model.api.CommentPost;
import utils.DTOtoBean;

@Service
public class BookService {
	
	private ConcurrentMap<Long, Book> books = new ConcurrentHashMap<>();
	private AtomicLong nextBookId = new AtomicLong();
	private AtomicLong nextCommentId = new AtomicLong();
		
	public BookService() {
		this.save(new BookPost("Atlas shrugged", "summary 1", "Ayn Rand", "Deusto", 2019));
		this.save(new BookPost("Harry Potter y la camara secreta", "summary 2", "J.K Rowling", "Salamandra", 1999));
		this.save(new BookPost("Contra la Teoría Monetaria Moderna", "summary 3", "Juan Ramon Rallo", "Deusto", 2017));
		this.save(new BookPost("Estados Fallidos", "summary 4", "Noam Chomsky", "B de Bolsillo", 2017));
		this.save(new BookPost("Viaje a la libertad economica", "summary 5", "Daniel Lacalle", "Deusto", 2013));
		this.save(new BookPost("Memoria del comunismo", "summary 6", "Federico Jimenez Los Santos", "Espasa", 2018));
		this.save(new BookPost("Liberalismo: Los 10 principios basicos del orden liberal", "summary 7", "Juan Ramon Rallo", "Deusto", 2019));
		this.save(new BookPost("Padre Rico, Padre pobre", "summary 8", "ROber T Kiyoaski", "De Bolsillo", 2020));
		this.save(new BookPost("Los años de Aznar", "summary 9", "Sergio Gomez Alba", "Cordoba", 2020));
		this.save(new BookPost("Los ricos de Franco", "summary 10", "Mariano Sanchez Soler", "Roca", 2020));
		this.save(new BookPost("Blockchain: La revolución industrial de Internet", "summary 11", "Alexander Preukschat", "Ediciones Gestion", 2017));
		this.put(0L, new CommentPost("Gran libro", "joaquin", 5));
	}

	public Book save(BookPost bookPost) {
		Book book = DTOtoBean.toBook(bookPost);
		long id = nextBookId.getAndIncrement();		
		book.setId(id);
		this.books.put(id, book);
		
		return book;		
	}
	
	public Book findById (Long id) {
		return this.books.get(id);
	}
	
	public List<Book> findAll(){
		return new ArrayList<Book>(this.books.values());
	}
	
	public Book put(Long idBook, CommentPost commentPost) {
		Book book = books.get(idBook);
		if (book!=null) {
			Comment comment = DTOtoBean.toComment(commentPost);
			long nextId = nextCommentId.getAndIncrement();
			comment.setId(nextId);
			if (book.getComments() == null) {
				book.setComments(new ArrayList<>());
			}
			else {
				book.getComments().add(comment);
			}
			return book;
		}
		return null;
	}
	
	public Comment delete(Long idBook, Long idComment) {
		Book book = books.get(idBook);
		if (book!=null) {
			Comment comment =  book.getComments().stream().filter(c -> c.getId().equals(idComment))
			  .findAny()
			  .orElse(null);
			book.getComments().removeIf(c -> c.getId().equals(idComment));
			return comment;			
		}
		return null;
	}

}

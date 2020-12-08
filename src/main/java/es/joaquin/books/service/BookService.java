package es.joaquin.books.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import es.joaquin.books.model.Book;
import es.joaquin.books.model.Comment;

@Service
public class BookService {
	
	private ConcurrentMap<Long, Book> books = new ConcurrentHashMap<>();
	private AtomicLong nextBookId = new AtomicLong();
	private AtomicLong nextCommentId = new AtomicLong();

		
	public BookService() {
		this.save(new Book("Atlas shrugged", "", "Ayn Rand", "Deusto", 2019));
		this.save(new Book("Atlas shrugged", "", "Ayn Rand", "Deusto", 2019));
	}

	public Book save(Book book) {
		
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
	
	public Book put(Long idBook, Comment comment) {
		Book book = books.get(idBook);
		if (book!=null) {
			long nextId = nextCommentId.getAndIncrement();
			comment.setId(nextId);
			book.getComments().add(comment);
			return book;
		}
		return null;
	}
	
	public Comment delete(Long idBook, Long idComment) {
		Book book = books.get(idBook);
		if (book!=null) {
			return book.getComments().stream().filter(c -> c.getId().equals(idComment))
			  .findAny()
			  .orElse(null);
		}
		return null;
	}
	
	
	
	

}

package es.joaquin.books.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.joaquin.books.entities.Book;
import es.joaquin.books.repository.BookRepository;

@Service
public class BookService {	
	
	@Autowired
	private BookRepository bookRepository;
	
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

	/*public Book save(BookPost bookPost) {
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
	}*/

}


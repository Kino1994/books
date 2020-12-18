package es.joaquin.books.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.joaquin.books.model.api.dto.BookDTO;
import es.joaquin.books.model.api.dto.CommentDTO;
import es.joaquin.books.model.api.request.BookRequest;
import es.joaquin.books.model.api.request.CommentRequest;
import es.joaquin.books.model.api.response.BookResponse;
import es.joaquin.books.service.BookService;

@RestController
@RequestMapping("/api/v1")
public class BookApiController implements BookApi {
	
	@Autowired
	private BookService bookService;
	
	private static final ModelMapper modelMapper = new ModelMapper();

	@Override
	public ResponseEntity<List<BookResponse>> findAll() {
		return new ResponseEntity<List<BookResponse>>(bookService.findAll().stream().map(bookdto -> modelMapper.map(bookdto, BookResponse.class))
			.collect(Collectors.toList()), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<BookResponse> add(BookRequest bookRequest) throws URISyntaxException {
		BookDTO book = modelMapper.map(bookRequest, BookDTO.class);
		book = bookService.save(book);
		URI location = new URI("/api/v1/books/" + book.getId());		
		return ResponseEntity.created(location).body(modelMapper.map(book,BookResponse.class));
	}

	@Override
	public ResponseEntity<BookResponse> findById(Long id) {
		Optional<BookDTO> book = bookService.findById(id);
		if (!book.isEmpty() && book.isPresent()) {
			return new ResponseEntity<BookResponse>(modelMapper.map(book.get(),BookResponse.class), HttpStatus.OK);
		}
		return ResponseEntity.notFound().build();
	}

	@Override
	public ResponseEntity<BookResponse> comment(Long id, @Valid CommentRequest commentRequest) throws URISyntaxException {
		CommentDTO comment = modelMapper.map(commentRequest, CommentDTO.class);
		Optional<BookDTO> book = bookService.comment(id, comment);
		if (!book.isEmpty() && book.isPresent()) {
			URI location = new URI("/api/v1/books/comments/" + book.get().getComments().get(book.get().getComments().size() -1).getId());		
			return ResponseEntity.created(location).body(modelMapper.map(book.get(),BookResponse.class));
		}
		return ResponseEntity.notFound().build(); 
	}

	@Override
	public ResponseEntity<Void> delete(Long commentId) {
		return bookService.delete(commentId).equals(true) ? ResponseEntity.noContent().build()
				: ResponseEntity.notFound().build(); 
	}
	
}
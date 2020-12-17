package es.joaquin.books.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.joaquin.books.model.api.request.BookRequest;
import es.joaquin.books.model.api.request.CommentRequest;
import es.joaquin.books.model.api.response.BookResponse;
import es.joaquin.books.service.BookService;

@RestController
@RequestMapping("/api/v1")
public class BookApiController implements BookApi {
	
	@Autowired
	private BookService bookService;

	@Override
	public ResponseEntity<List<BookResponse>> findAll() {

		return null;
	}

	@Override
	public ResponseEntity<BookResponse> add(BookRequest bookRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<BookResponse> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<BookResponse> put(Long id, @Valid CommentRequest commentRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> delete(Long id, Long commentId) {
		// TODO Auto-generated method stub
		return null;
	}

	
    
}

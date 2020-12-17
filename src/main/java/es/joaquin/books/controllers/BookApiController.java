package es.joaquin.books.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.joaquin.books.model.Book;
import es.joaquin.books.model.Comment;
import es.joaquin.books.model.api.BookPost;
import es.joaquin.books.model.api.CommentPost;
import es.joaquin.books.service.BookService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;

@RestController
@RequestMapping("/api/v1/books")
public class BookApiController implements BookApi {
	
	@Autowired
	private BookService bookService;

	@Override
    public ResponseEntity<List<Book>> findAll() {
    	return new ResponseEntity<List<Book>>(bookService.findAll(), HttpStatus.OK);
    }

	@Override
    public ResponseEntity<Void> delete(@Parameter(in = ParameterIn.PATH, required=true) @PathVariable("id") Long id,
    								@Parameter(in = ParameterIn.PATH, required=true) @PathVariable("comment_id") Long commentId) {
    	Comment comment = bookService.delete(id, commentId);
    	if (comment != null) {
    		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    	}
    	return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }
   
	@Override
    public ResponseEntity<Book> put(@Parameter(in = ParameterIn.PATH, description = "", required=true) @PathVariable("id") Long id, 
    						@Valid @RequestBody CommentPost commentPost) {
    	Book book = bookService.put(id, commentPost);
    	if (book != null) {
    		return new ResponseEntity<Book>(book,HttpStatus.OK);
    	}
    	return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
    }
    
	@Override
    public  ResponseEntity<Book> findById(@Parameter(in = ParameterIn.PATH, description = "", required=true) @PathVariable("id") Long id) {
    	Book book = bookService.findById(id);
    	if (book!=null) {
    		return new ResponseEntity<Book>(book, HttpStatus.OK);
    	}
    	return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
    }
   
	@Override
    public ResponseEntity<Book> add(@RequestBody BookPost bookPost) { 
    	return new ResponseEntity<Book>(bookService.save(bookPost), HttpStatus.CREATED);
    }
    
}

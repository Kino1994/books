package es.joaquin.books.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import es.joaquin.books.model.Book;
import es.joaquin.books.model.Comment;
import es.joaquin.books.model.api.BookInfo;
import es.joaquin.books.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class BookApiController {
	
	@Autowired
	private BookService bookService;

    @Operation(description = "Get all Books", tags={ "Books" })
    @JsonView(Book.Basico.class)
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Book.class)))),        
        @ApiResponse(responseCode = "500", description = "Internal server error") })
    @RequestMapping(value = "/books",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    public ResponseEntity<List<Book>> findAll() {
    	return new ResponseEntity<List<Book>>(bookService.findAll(), HttpStatus.OK);
    }


    @Operation(description = "Delete a comment from a book", tags={ "Books" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "204", description = "The comment was deleted successfully."),        
        @ApiResponse(responseCode = "404", description = "One of the specified resource was not found."),        
        @ApiResponse(responseCode = "500", description = "Internal server error") })
    @RequestMapping(value = "/books/{id}/comment/{comment_id}",
        method = RequestMethod.DELETE)
    public  ResponseEntity<Void> delete(@Parameter(in = ParameterIn.PATH, required=true) @PathVariable("id") Long id,
    									@Parameter(in = ParameterIn.PATH, required=true) @PathVariable("comment_id") Long commentId) {
    	Comment comment = bookService.delete(id, commentId);
    	if (comment != null) {
    		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    	}
    	return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }


    @Operation(description = "Add a comment to a book", tags={ "Books" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "The comment was created successfully.", content = @Content(schema = @Schema(implementation = Book.class))),
        @ApiResponse(responseCode = "404", description = "The specified book was not found."),
        @ApiResponse(responseCode = "500", description = "Internal server error") })
    @RequestMapping(value = "/books/{id}/comment",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<Book> put(@Parameter(in = ParameterIn.PATH, description = "", required=true) @PathVariable("id") Long id, 
    						@RequestBody Comment comment) {
    	Book book = bookService.put(id, comment);
    	if (book != null) {
    		return new ResponseEntity<Book>(book,HttpStatus.NO_CONTENT);
    	}
    	return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
    }


    @Operation(description = "Get Book by id", tags={ "Books" })
    @JsonView(Book.BookDetailWithComments.class)
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Book.class))),        
        @ApiResponse(responseCode = "404", description = "The specified book was not found.") })
    @RequestMapping(value = "/books/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    public  ResponseEntity<Book> findById(@Parameter(in = ParameterIn.PATH, description = "", required=true) @PathVariable("id") Long id) {
    	Book book = bookService.findById(id);
    	if (book!=null) {
    		return new ResponseEntity<Book>(book, HttpStatus.OK);
    	}
    	return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "", description = "Create a Book", tags={ "Books" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "The book was created successfully.", content = @Content(schema = @Schema(implementation = BookInfo.class))),
        
        @ApiResponse(responseCode = "400", description = "Bad request"),
        
        @ApiResponse(responseCode = "500", description = "Internal server error") })
    @RequestMapping(value = "/books",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<BookInfo> add(@Parameter(schema = @Schema(implementation = Book.class)) @RequestBody Book book) { 
    	return new ResponseEntity<BookInfo>(HttpStatus.CREATED);
    }
    
}

package es.joaquin.books.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.annotation.JsonView;

import es.joaquin.books.model.Book;
import es.joaquin.books.model.api.BookPost;
import es.joaquin.books.model.api.CommentPost;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface BookApi {
	
	@Operation(description = "Get all Books", tags={ "Books" })
    @JsonView(Book.Basico.class)
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successful operation"),        
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @RequestMapping(value = "/books",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Book>> findAll();

    @Operation(description = "Delete a comment from a book", tags={ "Books" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "204", description = "The comment was deleted successfully."),        
        @ApiResponse(responseCode = "404", description = "One of the specified resource was not found."),        
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @RequestMapping(value = "/books/{id}/comment/{comment_id}",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> delete(@Parameter(in = ParameterIn.PATH, required=true) @PathVariable("id") Long id,
    							@Parameter(in = ParameterIn.PATH, required=true) @PathVariable("comment_id") Long commentId);
    
    
    @Operation(description = "Add a comment to a book", tags={ "Books" })
    @JsonView(Book.BookDetailWithComments.class)
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "The comment was created successfully."),
        @ApiResponse(responseCode = "400", description = "Bad request"),
        @ApiResponse(responseCode = "404", description = "The specified book was not found."),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @RequestMapping(value = "/books/{id}/comment",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<Book> put(@Parameter(in = ParameterIn.PATH, description = "", required=true) @PathVariable("id") Long id, 
    						@Valid @RequestBody CommentPost commentPost);
    

    @Operation(description = "Get Book by id", tags={ "Books" })
    @JsonView(Book.BookDetailWithComments.class)
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "successful operation"),        
        @ApiResponse(responseCode = "404", description = "The specified book was not found.")
    })
    @RequestMapping(value = "/books/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Book> findById(@Parameter(in = ParameterIn.PATH, description = "", required=true) @PathVariable("id") Long id);
    

    @Operation(description = "Create a Book", tags={ "Books" })
    @JsonView(Book.BookDetail.class)
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "The book was created successfully."),        
        @ApiResponse(responseCode = "400", description = "Bad request"),        
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @RequestMapping(value = "/books",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Book> add(@RequestBody BookPost bookPost);

}

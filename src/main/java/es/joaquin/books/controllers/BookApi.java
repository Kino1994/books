package es.joaquin.books.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.annotation.JsonView;

import es.joaquin.books.model.api.request.BookRequest;
import es.joaquin.books.model.api.request.CommentRequest;
import es.joaquin.books.model.api.response.BookResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface BookApi {
	
	@Operation(description = "Get all Books")
    @JsonView(BookResponse.Basico.class)
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successful operation"),        
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @RequestMapping(value = "/books",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<BookResponse>> findAll();
	
	
	@Operation(description = "Create a Book")
    @JsonView(BookResponse.BookDetail.class)
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "The book was created successfully"),        
        @ApiResponse(responseCode = "400", description = "Bad request"),        
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @RequestMapping(value = "/books",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<BookResponse> add(@JsonView(BookRequest.BookDetail.class) @RequestBody BookRequest bookRequest);

    @Operation(description = "Get Book by id")
    @JsonView(BookResponse.BookDetailWithComments.class)
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successful operation"),        
        @ApiResponse(responseCode = "404", description = "The specified Book was not found")
    })
    @RequestMapping(value = "/books/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<BookResponse> findById(@Parameter(in = ParameterIn.PATH, description = "", required=true) @PathVariable("id") Long id);
    
    
    @Operation(description = "Add a Comment to a Book")
    @JsonView(BookResponse.BookDetailWithComments.class)
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "The comment was created successfully"),
        @ApiResponse(responseCode = "400", description = "Bad request"),
        @ApiResponse(responseCode = "404", description = "The specified Book was not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @RequestMapping(value = "/books/{id}/comment",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<BookResponse> put(@Parameter(in = ParameterIn.PATH, description = "", required=true) @PathVariable("id") Long id, 
    	    @JsonView(CommentRequest.Info.class) @Valid @RequestBody CommentRequest commentRequest);
    
   
    @Operation(description = "Delete a Comment from a Book")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "204", description = "The comment was deleted successfully"),        
        @ApiResponse(responseCode = "404", description = "The specified resource was not found"),        
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @RequestMapping(value = "/books/{id}/comment/{commentId}",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> delete(@Parameter(in = ParameterIn.PATH, required=true) @PathVariable("id") Long id,
    							@Parameter(in = ParameterIn.PATH, required=true) @PathVariable("commentId") Long commentId);

}

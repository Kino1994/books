package es.joaquin.books.controllers;

import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.annotation.JsonView;

import es.joaquin.books.model.api.request.UserRequest;
import es.joaquin.books.model.api.response.CommentResponse;
import es.joaquin.books.model.api.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface UserApi {
	
	@Operation(description = "Get all Users")
	@JsonView(UserResponse.UserDetail.class)
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successful operation"),        
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @RequestMapping(value = "/users",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<UserResponse>> findAll();
	
	
	@Operation(description = "Create a User")
	@JsonView(UserResponse.UserDetail.class)
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "The User was created successfully"),        
        @ApiResponse(responseCode = "400", description = "Bad request"), 
        @ApiResponse(responseCode = "304", description = "There is a User with that nick"),        
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @RequestMapping(value = "/users",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<UserResponse> add(@JsonView(UserRequest.User.class) @Valid @RequestBody UserRequest userRequest) throws URISyntaxException;
	
	@Operation(description = "Update User email")
	@JsonView(UserResponse.UserDetail.class)
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "The comment was created successfully"),
        @ApiResponse(responseCode = "400", description = "Bad request"),
        @ApiResponse(responseCode = "404", description = "The specified User was not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @RequestMapping(value = "/users",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<UserResponse> put(@JsonView(UserRequest.User.class) @Valid @RequestBody UserRequest UserRequest);
    

    @Operation(description = "Get User by id")
	@JsonView(UserResponse.UserDetail.class)
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successful operation"),        
        @ApiResponse(responseCode = "404", description = "The specified User was not found")
    })
    @RequestMapping(value = "/users/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<UserResponse> findById(@Parameter(in = ParameterIn.PATH, description = "", required=true) @PathVariable("id") Long id);
    
    
    @Operation(description = "Delete a User by id")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "204", description = "The User was deleted successfully"),        
        @ApiResponse(responseCode = "404", description = "The specified User was not found"),        
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @RequestMapping(value = "/users/{id}",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> delete(@Parameter(in = ParameterIn.PATH, required=true) @PathVariable("id") Long id);
    
    @Operation(description = "Get Comments by User id")
	@JsonView(CommentResponse.CommentFullDetail.class)
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successful operation"),        
        @ApiResponse(responseCode = "404", description = "The specified User was not found")
    })
    @RequestMapping(value = "/users/{id}/comments",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<CommentResponse>> findCommentsByUserId(@Parameter(in = ParameterIn.PATH, description = "", required=true) @PathVariable("id") Long id);
    

}

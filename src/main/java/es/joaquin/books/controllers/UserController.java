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

import es.joaquin.books.model.api.dto.UserDTO;
import es.joaquin.books.model.api.request.UserRequest;
import es.joaquin.books.model.api.response.CommentResponse;
import es.joaquin.books.model.api.response.UserResponse;
import es.joaquin.books.service.UserService;

@RestController
@RequestMapping("/api/v1")
public class UserController implements UserApi{
	
	@Autowired
	private UserService userService;
		
	private static final ModelMapper modelMapper = new ModelMapper();

	@Override
	public ResponseEntity<List<UserResponse>> findAll() {
		return new ResponseEntity<List<UserResponse>>(userService.findAll().stream().map(userdto -> modelMapper.map(userdto, UserResponse.class))
		.collect(Collectors.toList()), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UserResponse> add(UserRequest userRequest) throws URISyntaxException {
		UserDTO user = modelMapper.map(userRequest,UserDTO.class);
		Optional<UserDTO> userOptional = userService.save(user);
		if (!userOptional.isEmpty() && userOptional.isPresent()) {
			URI location = new URI("/api/v1/users/" + user.getId());		
			return ResponseEntity.created(location).body(modelMapper.map(user,UserResponse.class));
		}
		return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	}

	@Override
	public ResponseEntity<UserResponse> patch(@Valid UserRequest userRequest) {
		UserDTO user = modelMapper.map(userRequest, UserDTO.class);
		Optional<UserDTO> userOptional = userService.update(user);
		if (!userOptional.isEmpty() && userOptional.isPresent()) {
			return new ResponseEntity<UserResponse>(modelMapper.map(userOptional.get(),UserResponse.class), HttpStatus.OK);
		}
		return ResponseEntity.notFound().build();
	}

	@Override
	public ResponseEntity<UserResponse> findById(Long id) {
		Optional<UserDTO> user = userService.findById(id);
		if (!user.isEmpty() && user.isPresent()) {
			return new ResponseEntity<UserResponse>(modelMapper.map(user.get(),UserResponse.class), HttpStatus.OK);
		}
		return ResponseEntity.notFound().build();
	}

	@Override
	public ResponseEntity<Void> delete(Long id) {
		return userService.delete(id).equals(true) ? ResponseEntity.noContent().build()
			: ResponseEntity.notFound().build();  		
	}

	@Override
	public ResponseEntity<List<CommentResponse>> findCommentsByUserId(Long id) {
		return new ResponseEntity<List<CommentResponse>>(userService.findCommentsByUserId(id).stream()
			.map(comment -> modelMapper.map(comment, CommentResponse.class)).collect(Collectors.toList()),HttpStatus.OK);
	}

}

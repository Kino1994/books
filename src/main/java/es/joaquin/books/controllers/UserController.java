package es.joaquin.books.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.joaquin.books.model.api.request.UserRequest;
import es.joaquin.books.model.api.response.UserResponse;
import es.joaquin.books.service.UserService;

@RestController
@RequestMapping("/api/v1")
public class UserController implements UserApi{
	
	@Autowired
	private UserService userService;

	@Override
	public ResponseEntity<List<UserResponse>> findAll() {

		return null;
	}

	@Override
	public ResponseEntity<UserResponse> add(UserRequest userRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<UserResponse> put(@Valid UserRequest UserRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<UserResponse> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}

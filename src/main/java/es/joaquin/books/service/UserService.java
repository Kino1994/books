package es.joaquin.books.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.joaquin.books.entities.User;
import es.joaquin.books.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@PostConstruct
	public void init () {		
		userRepository.save(new User(null,"joaquin", "ja.devicentel@alumnos.urjc.es"));
		userRepository.save(new User(null,"joaquindevicente", "joaquindevicente@hotmail.es"));
		userRepository.save(new User(null,"kino_1994", "kino110494@gmail.com"));
	}

}

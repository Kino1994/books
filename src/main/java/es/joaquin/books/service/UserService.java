package es.joaquin.books.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import es.joaquin.books.entities.Comment;
import es.joaquin.books.entities.User;
import es.joaquin.books.model.api.dto.CommentDTO;
import es.joaquin.books.model.api.dto.UserDTO;
import es.joaquin.books.repository.CommentRepository;
import es.joaquin.books.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CommentRepository commentRepository;	
	
	private static final ModelMapper modelMapper = new ModelMapper();
	
	@PostConstruct
	public void init () {		
		userRepository.save(new User(null,"joaquin", "ja.devicentel@alumnos.urjc.es"));
		userRepository.save(new User(null,"joaquindevicente", "joaquindevicente@hotmail.es"));
		userRepository.save(new User(null,"kino_1994", "kino110494@gmail.com"));
	}
	
	public List<UserDTO> findAll(){
		return userRepository.findAll().stream().map(user -> modelMapper.map(user,  UserDTO.class)).collect(Collectors.toList());
	}
	
	public Optional<UserDTO> findById(Long id){
		Optional<User> user = userRepository.findById(id);
		if (!user.isEmpty() &&  user.isPresent()) {
			return Optional.of(modelMapper.map(userRepository.save(user.get()), UserDTO.class));		
		}
		return Optional.empty();
	}
	
	public Optional<UserDTO> save(UserDTO userDTO) {
		Optional<User> user = userRepository.findByNick(userDTO.getNick());
		if (!user.isPresent()) {
			user = Optional.of(User.builder().nick(userDTO.getNick()).email(userDTO.getEmail()).build());
			return Optional.of(modelMapper.map(userRepository.save(user.get()),UserDTO.class)); 
		}
		return Optional.empty();
	}
	
	public Optional<UserDTO> update(UserDTO userDTO) {
		Optional<User> user = userRepository.findByNick(userDTO.getNick());
		if (!user.isEmpty() && user.isPresent()) {
			user.get().setEmail(userDTO.getEmail());
			return Optional.of(modelMapper.map(userRepository.save(user.get()), UserDTO.class));	
		}
		return Optional.empty();
	}
	
	public Boolean delete(Long id) {
		Optional<User> user = userRepository.findById(id);
		if (!user.isEmpty() && user.isPresent()) {
			if (this.findCommentsByUserId(user.get().getId()).isEmpty()) {
				userRepository.delete(user.get());
				return true;
			}
			throw new InvalidDataAccessApiUsageException("Users with comments can not be deleted");
		}
		return false;
	}
	
	public List<CommentDTO> findCommentsByUserId(Long userId){
		Optional<User> user = userRepository.findById(userId);
		
		if (!user.isEmpty() && user.isPresent()) {
			List<Comment> comments = commentRepository.findAllByUser(user.get());
			return comments.stream().map(comment -> 
				modelMapper.map(comment, CommentDTO.class).setBookId(comment.getBook().getId()))
				.collect(Collectors.toList());
		}
		return Collections.emptyList();
		
	};

}

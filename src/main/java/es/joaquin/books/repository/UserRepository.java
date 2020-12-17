package es.joaquin.books.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.joaquin.books.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByNick(String nick);

}

package es.joaquin.books.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.joaquin.books.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}

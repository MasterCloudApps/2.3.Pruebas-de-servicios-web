package es.urjc.code.daw.library.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.urjc.code.daw.library.models.User;

/**
 * Repository to manage users in database.
 * 
 * NOTE: This interface is intended to be extended by developer adding new
 * methods. Current method can not be removed because it is used in
 * authentication procedures.
 */
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String name);

}

package es.urjc.code.daw.library.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.urjc.code.daw.library.models.ERole;
import es.urjc.code.daw.library.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}

package mock.project.frontend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mock.project.frontend.entities.Roles;

@Repository(value="roleRepo")
public interface RoleRepository extends JpaRepository<Roles, Integer> {

}

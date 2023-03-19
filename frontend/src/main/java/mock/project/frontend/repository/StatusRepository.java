package mock.project.frontend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mock.project.frontend.entities.Status;

public interface StatusRepository extends JpaRepository<Status, Integer> {

}

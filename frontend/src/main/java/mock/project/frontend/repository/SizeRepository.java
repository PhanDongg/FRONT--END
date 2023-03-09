package mock.project.frontend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mock.project.frontend.entities.Sizes;
@Repository(value="sizeRepo")
public interface SizeRepository  extends JpaRepository<Sizes, Integer>{

}

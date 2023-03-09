package mock.project.frontend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mock.project.frontend.entities.Categories;

@Repository(value="catRepo")
public interface CategoryRepository extends JpaRepository<Categories, Integer> {

}

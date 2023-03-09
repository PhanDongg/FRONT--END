package mock.project.frontend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mock.project.frontend.entities.Images;

@Repository(value = "imageRepo")
public interface ImageRepository extends JpaRepository<Images, Integer>{

}

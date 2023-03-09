package mock.project.frontend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mock.project.frontend.entities.Users;

@Repository(value="userRepo")
public interface UserRepository  extends JpaRepository<Users, Integer>{
	
	Users findByUserNameAndPassword(String userName, String password);
}
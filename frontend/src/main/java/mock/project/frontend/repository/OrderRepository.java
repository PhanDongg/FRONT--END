package mock.project.frontend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mock.project.frontend.entities.Orders;
@Repository(value="orderRepo")
public interface OrderRepository extends JpaRepository<Orders, Integer>{
	
	List<Orders> findByUser(Integer userId);
	
	Optional<Orders> findById(Integer id);
}

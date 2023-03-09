package mock.project.frontend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mock.project.frontend.entities.Products;

@Repository(value="productRepo")
public interface ProductRepository extends JpaRepository<Products, Integer> {
	
	List<Products> findByProductName(String productName);
//	 List<Products> findAllByPrice(double price, Pageable pageable);

}

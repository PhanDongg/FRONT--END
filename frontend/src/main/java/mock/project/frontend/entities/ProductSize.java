package mock.project.frontend.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="product_size")
public class ProductSize {
	
	@Id
	@Column(name = "product_size_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productSizeId;
//	
//	@ManyToOne
//	@JoinColumn(name = "product_id",referencedColumnName = "product_id")
//	private Products product;
//	
//	@ManyToOne
//	@JoinColumn(name = "size_id", referencedColumnName = "size_id")
//	private Sizes size;
	
	

}

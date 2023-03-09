package mock.project.frontend.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="order_detail")
public class OrderDetails implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "order_detail_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderDetailId;
	
	@Column(name="quantity",nullable = false, length = 50)
	private int quantity;
	
	@ManyToOne
	@JoinColumn(name = "product_id",referencedColumnName = "product_id")
	private Products product;
	
	@ManyToOne
	@JoinColumn(name = "order_id", referencedColumnName = "order_id")
	private Orders order;
	

}

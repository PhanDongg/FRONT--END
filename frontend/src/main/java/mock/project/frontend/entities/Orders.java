package mock.project.frontend.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name="orders")
public class Orders implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "order_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderId;	
	
	@Column(name="order_date", length = 50)
	private Date orderDate;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "status_id", referencedColumnName = "status_id")
	private Status status;
	
	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName = "user_id")
	private Users user;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy="order")
	private Set<OrderDetails> orderDetails;
}

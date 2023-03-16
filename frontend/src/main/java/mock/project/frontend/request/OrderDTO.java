package mock.project.frontend.request;

import java.sql.Date;
import java.util.Set;

import mock.project.frontend.entities.OrderDetails;
import mock.project.frontend.entities.Users;


public class OrderDTO {
	
	private Integer orderId;
	private Date orderDate;
	private Users user;
	private Set<OrderDetails> orderDetails;
	
	
}

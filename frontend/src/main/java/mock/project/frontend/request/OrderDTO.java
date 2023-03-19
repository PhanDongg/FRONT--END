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
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public Set<OrderDetails> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(Set<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}
	
}

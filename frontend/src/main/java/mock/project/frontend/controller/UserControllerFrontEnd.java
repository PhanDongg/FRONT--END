package mock.project.frontend.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import mock.project.frontend.entities.Users;
import mock.project.frontend.services.UserService;

@Controller
public class UserControllerFrontEnd {
private Logger logger = Logger.getLogger(Users.class);
	
	@Autowired
	private UserService userService;
	
//	Customer Interface
	
	@GetMapping("/home-page")
	public String welcome() {
		return "home-page";
	}
	
	@GetMapping("/register-page")
	public String register() {
		return "register-2";
	}
	
	@GetMapping("/cart-page") 
	public String viewCart() {
		return "cart-page";
	}
	
	@GetMapping("/product-detail-page")
	public String viewProductDetail() {
		return "product-detail-page";
	}
	
	@GetMapping("/customer-detail-page")
	public String updateCustomerInfo() {
		return "customer-detail-page";
	}
	
	@GetMapping("/check-order-page")
	public String checkOrder() {
		return "check-order";
	}
	
//	Admin Interface
	
	@GetMapping("/dashboard")
	public String viewDashboard() {
		return "dashboard";
	}
	
	// Admin: order
	
	@GetMapping("/order-dashboard")
	public String viewOrderDashboard() {
		return "order-dashboard";
	}
	
	@GetMapping("/order-list")
	public String viewOrderList() {
		return "order-list";
	}
	
	// Admin: product
	
	@GetMapping("/product-dashboard")
	public String viewProductDashboard() {
		return "product-dashboard";
	}
	
//	@GetMapping("/product-list")
//	public String viewProductList() {
//		return "product-list";
//	}
	
	@GetMapping("/add-product")
	public String addProduct() {
		return "add-product";
	}
	
	// Admin: user
	
	
}

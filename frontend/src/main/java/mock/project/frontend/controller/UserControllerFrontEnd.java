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
	
	@GetMapping("/home")
	public String welcome() {
		return "home-page";
	}
	
	@GetMapping("/register")
	public String register() {
		return "register-page";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login-page";
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
}

package mock.project.frontend.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import mock.project.frontend.services.UserService;

@Controller
public class UserControllerFrontEnd {
private Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
//	@GetMapping({ "/", "/welcome-page" })
//	public String welcome() {
//		return "welcome-page";
//	}
	
	@GetMapping("/welcome-page")
	public String welcome() {
		return "welcome-page";
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
}

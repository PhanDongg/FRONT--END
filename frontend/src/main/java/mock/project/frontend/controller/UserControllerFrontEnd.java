package mock.project.frontend.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mock.project.frontend.entities.Users;
import mock.project.frontend.services.UserService;

@Controller
//@RequestMapping("/")
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
	
	
	@PostMapping("/login")
	public String checkLogintest() {
		return "redirect:/home";
	}
	
	@GetMapping("/collection")
	public String collection() {
		return "collection-page";
	}
	
	
	@GetMapping("/cart") 
	public String viewCart() {
		return "cart-page";
	}
	
	@GetMapping("/product/detail")
	public String viewProductDetail() {
		return "product-detail-page";
	}
	
	@GetMapping("/account/info")
	public String updateCustomerInfo() {
		return "customer-detail-page";
	}
	
	@GetMapping("/account/order")
	public String checkOrder() {
		return "check-order-page";
	}
	
	@GetMapping("/account/order/detail")
	public String viewOrderDetail() {
		return "order-view-page";
	}
	
	@GetMapping("/add-product")
	public String addOrder() {
		return "add-product";
	}
}

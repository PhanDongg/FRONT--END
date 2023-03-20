package mock.project.frontend.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import mock.project.frontend.entities.Users;
import mock.project.frontend.request.ProductDTO;
import mock.project.frontend.request.UserDTO;

@Controller
//@RequestMapping("/")
public class UserControllerFrontEnd {
private Logger logger = Logger.getLogger(Users.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/home")
	public String home() {
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
	
//	admin
	
	@GetMapping("/dashboard")
	public String viewDashboard() {
		return "dashboard";
	}
	
	@GetMapping("/edit-product")
	public String editProduct() {
		return "edit-product";
	}
	
	@GetMapping("/add-product")
	public String addOrder() {
		return "add-product";
	}
	
	@GetMapping("/product-dashboard")
	public String viewProductDashboard() {
		return "product-dashboard";
	}
	
	@GetMapping("/order-dashboard")
	public String viewOrderDashboard() {
		return "order-dashboard";
	}
	
	@GetMapping("/order-list")
	public String viewOrderList() {
		return "order-list";
	}
	
	@GetMapping("/edit-order")
	public String editOrder() {
		return "edit-order";
	}
	
	@GetMapping("/user-list")
	public String viewUserList() {
		return "user-list";
	}
}

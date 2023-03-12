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
	
}

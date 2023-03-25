package mock.project.frontend.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import mock.project.frontend.request.OrderDTO;
import mock.project.frontend.request.UserDTO;
import mock.project.frontend.request.UserDTOReponse;

@Controller
public class UserController {
	private Logger logger = Logger.getLogger(UserController.class);
	@Autowired
	private RestTemplate  restTemplate;
	
	@Value("${product.api.url}")
	private String productApi;

	@Value("${authentication.api.url}")
	private String authenticationApi;
	
	@Value("${user.api.url}")
	private String userApi;
	
	@Value("${admin.api.url}")
	private String adminApi;
	
	@Value("${order.api.url}")
	private String orderApi;
	
	//orders of user
	@GetMapping("/user/order")
	public String listOrdersUser(Model model,HttpServletRequest request) {
		logger.info("Loading order list views.....");
		String url = userApi+ "/order";	
		if(request.getAttribute("username")==null) {
			return "home-page";
		}
		try {
			ResponseEntity<OrderDTO[]> response = restTemplate.getForEntity(url, OrderDTO[].class);
			OrderDTO[] listOrders = response.getBody();
			model.addAttribute("listOrders", listOrders);
			return "check-order-page";
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "error";
		}
	}
	//user infor
	@GetMapping("/user/info")
	public String getUserInfo(Model model,HttpSession session) {
		logger.info("Loading order list views.....");
		String username = (String)session.getAttribute("username");
		String url = userApi + "/info?username="+ username;
		UserDTOReponse userInfo = restTemplate.getForObject(url, UserDTOReponse.class);
		model.addAttribute("user", userInfo);
		return "customer-detail-page";
	}

}

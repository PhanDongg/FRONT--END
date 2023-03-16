package mock.project.frontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import mock.project.frontend.request.UserDTO;

@Controller
public class UserManagementController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${product.api.url}")
	private String productApi;
	
	@Value("${user.api.url}")
	private String userApi;
	
	@GetMapping("/user-list")
	public String userList(Model model) {
		String url = userApi;
		ResponseEntity<UserDTO[]> response = restTemplate.getForEntity(url, UserDTO[].class);
		UserDTO[] listUsers = response.getBody();
		model.addAttribute("listUsers", listUsers);
		return "user-list";
	}
}

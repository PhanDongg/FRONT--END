package mock.project.frontend.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import mock.project.frontend.request.User;
import mock.project.frontend.request.UserDTO;
import mock.project.frontend.response.ResponseTransfer;

@Controller
public class LoginAndRegisterController {
	@Autowired
	private RestTemplate restTemplate;

	@Value("${user.api.url}")
	private String userApi;

	@Value("${authentication.api.url}")
	private String authenticationApi;

	// Register new member
	@GetMapping("/register")
	public String registerViews(Model model) {
//		logger.info("Loading register form...");
		model.addAttribute("user", new UserDTO());
		return "register-page";
	}
	
	@PostMapping("/register")
	public String registerNewMember(Model model, @ModelAttribute("user") @Valid UserDTO user) {
//			logger.info("Processing...");
		String url = userApi + "/register";
		ResponseEntity<ResponseTransfer> response = restTemplate.postForEntity(url, user, ResponseTransfer.class);
		model.addAttribute("msg", response);
		return "register-page";
	}

	@PostMapping("/login")
	public String checkLogin(@ModelAttribute("user") User user, HttpServletRequest resquest,
			HttpServletResponse response, Model model) throws UnsupportedEncodingException {
		String url = authenticationApi + "/login";
		ResponseEntity<String> token = restTemplate.postForEntity(url, user, String.class);
		if (token != null) {
			Cookie tokenCookie = new Cookie("Authorization", URLEncoder.encode("Bearer " + token, "UTF-8"));
			resquest.getSession().setAttribute("username", user.getUsername());
			tokenCookie.setMaxAge(24 * 60 * 60);
			response.addCookie(tokenCookie);
			return "home-page";
		}
		return "login-page";
	}

	@GetMapping("/login")
	public String loginForm(Model model) {
//			logger.info("Loading login form.....");
		model.addAttribute("user", new User());
		return "login-page";
	}

}

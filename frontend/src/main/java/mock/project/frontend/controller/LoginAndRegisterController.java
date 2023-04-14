package mock.project.frontend.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import mock.project.frontend.request.UserDTO;
import mock.project.frontend.validation.ValidationError;

@Controller
public class LoginAndRegisterController {

	private Logger logger = Logger.getLogger(LoginAndRegisterController.class);

	@Autowired
	private RestTemplate restTemplate;

	@Value("${user.api.url}")
	private String userApi;

	@Value("${authentication.api.url}")
	private String authenticationApi;

	Gson gson = new Gson();
	Map<String, String> map = new HashMap<String, String>();
	List<ValidationError> errors = new ArrayList<ValidationError>();
	String keySet = null;

	// register view
	@GetMapping("/register")
	public String registerViews(Model model) {
		logger.info("Loading register form...");
		return viewPage(null, null, "register-page", model);
	}

	// register new member front-end
	@PostMapping("/register")
	public String registerNewMember(Model model, @ModelAttribute("user") UserDTO user, BindingResult bindingResult) {
		logger.info("Processing...");
		String url = userApi + "/register";
		ResponseEntity<?> response = restTemplate.postForEntity(url, user, String.class);
		handleMapFromResponseEntity(response);
		return checkFormRegister(errors, keySet, model);
	}

	public String checkFormRegister(List<ValidationError> errors, String keySet, Model model) {
		if (errors.size() == 0) {
//			model.addAttribute("globalErr", keySet);
//			return "login-page";
			return "redirect:/login";
		} else if (errors.size() == 1 && checkCodeError(errors, 0, 0)) {
			if (checkFieldError(errors, 0, 1, "userName", null)) {
				return viewPage("validUserName", errors.get(0).getDefaultMessage(), "register-page", model);
			} else {
				return viewPage("validEmail", errors.get(0).getDefaultMessage(), "register-page", model);
			}
		} else if (errors.size() == 2 && (checkCodeError(errors, 0, 1)) || (checkCodeError(errors, 1, 0))) {
			if (checkFieldError(errors, 0, 1, "userName", "email")) {
				model.addAttribute("validUserName", errors.get(0).getDefaultMessage());
				model.addAttribute("validEmail", errors.get(1).getDefaultMessage());
				return viewPage(null, null, "register-page", model);
			}
			model.addAttribute("validUserName", errors.get(1).getDefaultMessage());
			model.addAttribute("validEmail", errors.get(0).getDefaultMessage());
			return viewPage(null, null, "register-page", model);
		} else {
			return viewPage("globalErr", keySet.concat(", Vui lòng kiểm tra lại!"), "register-page", model);
		}
	}

	public Boolean checkCodeError(List<ValidationError> errors, int x, int y) {
		return (errors.get(x).getCode().equals("ValidatedDuplicateRegister")
				&& errors.get(y).getCode().equals("ValidatedDuplicateRegister"));
	}

	public Boolean checkFieldError(List<ValidationError> errors, int x, int y, String field1, String field2) {
		if (field2 == null) {
			return (errors.get(x).getField().equals(field1));
		}
		return (errors.get(x).getField().equals(field1) && errors.get(y).getField().equals(field2));
	}

	// load login form
	@GetMapping("/login")
	public String loginForm(Model model) {
		logger.info("Loading login form.....");
		return viewPage(null, null, "login-page", model);
	}

	// check login
	@PostMapping("/login")
	public String checkLogin(@ModelAttribute("user") UserDTO user, HttpServletRequest request,
			HttpServletResponse response, Model model) throws UnsupportedEncodingException {
		String url = authenticationApi + "/login";
		ResponseEntity<?> token = restTemplate.postForEntity(url, user, String.class);
		handleMapFromResponseEntity(token);
		return checkFormLogin(request, response, model, user);
	}

	public String checkFormLogin(HttpServletRequest request, HttpServletResponse response, Model model, UserDTO user) {
		if (!keySet.equals("failure") && errors.size() == 0) {
			encodeAuthorization(request, response, user);
			return "redirect:/";
		} else if (errors.size() > 0) {
			return viewPage("globalErr", "Vui lòng điền đầy đủ Tên tài khoản và Mật khẩu", "login-page", model);
		} else {
			return viewPage("globalErr", "Tên tài khoản hoặc mật khẩu không chính xác", "login-page", model);
		}
	}

	public void encodeAuthorization(HttpServletRequest request, HttpServletResponse response, UserDTO user) {
		try {
			Cookie tokenCookie = new Cookie("Authorization", URLEncoder.encode("Bearer " + keySet, "UTF-8"));
			request.getSession().setAttribute("username", user.getUserName());
			tokenCookie.setMaxAge(24 * 60 * 60);
			response.addCookie(tokenCookie);
		} catch (UnsupportedEncodingException e) {
			System.err.println(e.getMessage());
		}
	}

	// logout
	@GetMapping("/logout")
	public String logout(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String url = authenticationApi + "/logout";
		String msg = restTemplate.getForObject(url, String.class);
		Cookie cookie = null;
		Cookie[] cookies = null;
		cookies = request.getCookies();
		session.removeAttribute("username");
		AdminController.jwt = null;
		System.out.println(AdminController.jwt);
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				cookie = cookies[i];
				if ((cookie.getName()).compareTo("Authorization") == 0) {
					cookie.setMaxAge(0);
					response.addCookie(cookie);
					break;
				}
			}
			return "redirect:/login";
		}
		return "error";
	}

	public String viewPage(String attributeName, String message, String page, Model model) {
		if (message == null) {
			model.addAttribute("user", new UserDTO());
			return page;
		} else {
			model.addAttribute(attributeName, message);
			model.addAttribute("user", new UserDTO());
			return page;
		}
	}

	public Map<String, String> convertJsonToMap(String responseEntity, Gson gson, Map<String, String> map) {
		return map = gson.fromJson(responseEntity, new TypeToken<Map<String, String>>() {}.getType());
	}

	public List<ValidationError> convertJsonToList(String responseEntity, Gson gson, List<ValidationError> errors) {
		return errors = gson.fromJson(responseEntity, new TypeToken<ArrayList<ValidationError>>() {}.getType());
	}

	public static String removeFirstAndLastChar(String str) {
		return str.substring(1, str.length() - 1);
	}

	public void handleMapFromResponseEntity(ResponseEntity<?> responseEntity) {
		map = convertJsonToMap(responseEntity.getBody().toString(), gson, map);
		errors = convertJsonToList(removeFirstAndLastChar(map.values().toString()), gson, errors);
		keySet = removeFirstAndLastChar(map.keySet().toString());
		System.out.println("map: " + map);
	}
}

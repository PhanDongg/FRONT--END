package mock.project.frontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import mock.project.frontend.request.ProductDTO;


@Controller
public class CartController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/cart")
	public String viewCart(@CookieValue(name = "cookieProduct", defaultValue = "") String cookieProduct) {
//		Gson gson = new Gson();
//		ProductDTO product = gson.fromJson("cookieProduct", ProductDTO.class);
//		System.out.println(product);
		return "cart-page";
	}
}

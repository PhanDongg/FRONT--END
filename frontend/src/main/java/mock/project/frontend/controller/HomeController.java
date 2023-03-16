package mock.project.frontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import mock.project.frontend.request.ProductDTO;
@Controller
public class HomeController {
	@Autowired
	private RestTemplate restTemplate;

	@Value("${product.api.url}")
	private String productApi;

	@Value("${user.api.url}")
	private String userApi;
	
	@GetMapping("/")
	public String homePage(Model model) {
		String url  = productApi;
		ResponseEntity<ProductDTO[]> response  = restTemplate.getForEntity(url, ProductDTO[].class);
		ProductDTO[] listProducts = response.getBody();
		model.addAttribute("listProducts", listProducts);
		return "home-page";
	}
}

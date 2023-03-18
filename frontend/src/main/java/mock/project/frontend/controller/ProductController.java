package mock.project.frontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import mock.project.frontend.request.ProductDTO;

@Controller
public class ProductController {

	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${product.api.url}")
	private String productApi;
	
	@GetMapping("/product-list")
	public String viewProductList(Model model) {
		String url = productApi;
		ResponseEntity<ProductDTO[]> response = restTemplate.getForEntity(url, ProductDTO[].class);
		ProductDTO[] listProducts = response.getBody();
		model.addAttribute("listProducts", listProducts);
		return "product-list";
	}
	
}

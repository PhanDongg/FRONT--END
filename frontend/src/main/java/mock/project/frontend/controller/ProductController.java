package mock.project.frontend.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import mock.project.frontend.request.ProductDTO;

@Controller
public class ProductController {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${product.api.url}")
	private String productApi;

	@GetMapping("/product/list")
	public String viewProductList(Model model) {
		String url = productApi;
		ResponseEntity<ProductDTO[]> response = restTemplate.getForEntity(url, ProductDTO[].class);
		ProductDTO[] listProducts = response.getBody();
		model.addAttribute("listProducts", listProducts);
		return "product-list";

	}

	@GetMapping("/category/{id}")
	public String getProductByCategory(@PathVariable(name = "id", required = false) Integer id, Model model) {
		String url = productApi + "/category/" + id;
		ResponseEntity<ProductDTO[]> response = restTemplate.getForEntity(url, ProductDTO[].class);
		ProductDTO[] listProducts = response.getBody();
		model.addAttribute("listProducts", listProducts);
		return "collection-page";
	}

	@GetMapping("/product/{id}")
	public String getProductDetails(@PathVariable(name = "id", required = false) Integer id, Model model) {
		String url = productApi + "/" + id;
		ProductDTO product = restTemplate.getForObject(url, ProductDTO.class);
		model.addAttribute("product", product);
		return "product-detail-page";
	}

	@PostMapping("/product/{id}")
	public String getSize(Model model, @PathVariable(name = "id", required = false) Integer id,
			@RequestParam(name = "size") String size) {
		String url = productApi + "/" + id;
		ProductDTO product = restTemplate.getForObject(url, ProductDTO.class);
		model.addAttribute("product", product);
		model.addAttribute("item-cart", product);
		System.out.println(size);
		return "product-detail-page";
	}
}

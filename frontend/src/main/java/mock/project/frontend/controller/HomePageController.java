package mock.project.frontend.controller;



import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import mock.project.frontend.request.ProductDTO;

@Controller
public class HomePageController {
	
	@Autowired
	private RestTemplate  restTemplate;
	
	@Value("${product.api.url}")
	private String productApi;

	@GetMapping("/home")
	public String homePage(Model model) {
		String url = productApi ;
		ResponseEntity<ProductDTO[]> response = restTemplate.getForEntity(url, ProductDTO[].class);
		ProductDTO[] listProducts = response.getBody();
		
//		model.addAttribute("userName", loginedUser.getUsername());
		for(ProductDTO product: listProducts) {
			System.out.println(product);
		}
		
		model.addAttribute("listProducts1", limitList(listProducts, 0));
		model.addAttribute("listProducts2", limitList(listProducts, 5));
		model.addAttribute("listProducts3", limitList(listProducts, 10));
		return "home-page";
	}
	

	public List<ProductDTO> limitList(ProductDTO[] listProducts, int itemIndex) {
		List<ProductDTO> listNew = new ArrayList<>();
		for (int i = itemIndex ; i<(itemIndex+5); i++) {
			 listNew.add(listProducts[i]);
		}
		return listNew;
	}
}

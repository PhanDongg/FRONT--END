package mock.project.frontend.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.google.gson.Gson;

import mock.project.frontend.request.ProductDTO;
import mock.project.frontend.request.SizeDTO;

@Controller
public class ProductController {

	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${product.api.url}")
	private String productApi;
	
	Cookie cookie = null;
	
	@GetMapping("/product/list")
	public String viewProductList(Model model) {
		String url = productApi;
		ResponseEntity<ProductDTO[]> response = restTemplate.getForEntity(url, ProductDTO[].class);
		ProductDTO[] listProducts = response.getBody();
		model.addAttribute("listProducts", listProducts);
		return "product-list";
	}
	
	@GetMapping("/category/{id}")
		public String getProductByCategory(@PathVariable(name="id", required = false)Integer id, Model model) {
			String url = productApi + "/category/" + id;
			ResponseEntity<ProductDTO[]> response = restTemplate.getForEntity(url, ProductDTO[].class);
			ProductDTO[] listProducts = response.getBody();
			model.addAttribute("listProducts", listProducts);
			return "collection-page";
	}
	
	@GetMapping("/product/{id}")
	public String getProductDetails(@PathVariable(name="id", required = false)Integer id, Model model) {
		String url = productApi + "/" + id;
		ProductDTO product = restTemplate.getForObject(url, ProductDTO.class);
		model.addAttribute("product", product);
		
		String urlProduct = productApi ;
		ResponseEntity<ProductDTO[]> response = restTemplate.getForEntity(urlProduct, ProductDTO[].class);
		ProductDTO[] listProducts = response.getBody();
		model.addAttribute("listProducts1", limitList(listProducts, 0, 5));
		model.addAttribute("listProducts2", limitList(listProducts, 5, 5));
		model.addAttribute("listProducts3", limitList(listProducts, 10, 5));
		
		model.addAttribute("listProducts4", limitList(listProducts, 6, 3));
		
		return "product-detail-page";
	}
	
	public List<ProductDTO> limitList(ProductDTO[] listProducts, int itemStartIndex, int numberOfItem) {
		List<ProductDTO> listNew = new ArrayList<>();
		for (int i = itemStartIndex ; i<(itemStartIndex + numberOfItem); i++) {
			 listNew.add(listProducts[i]);
		}
		return listNew;
	}
		

	@PostMapping("/product/{id}")
	public String getSize(Model model,@PathVariable(name="id", required = false)Integer id, @RequestParam(name = "size") String size, HttpServletResponse response) throws UnsupportedEncodingException{
		String url = productApi + "/" + id;
		ProductDTO product = restTemplate.getForObject(url, ProductDTO.class);
		model.addAttribute("product", product);
		System.out.println(size);
		
//		try {
//			Gson gson = new Gson();
//			cookie = new Cookie("cookieProduct", URLEncoder.encode(gson.toJson(product), "UTF-8"));
//			cookie.setMaxAge(7 * 24 * 60 * 60);
//			response.addCookie(cookie);
//		} catch (UnsupportedEncodingException e) {
//			System.err.println(e.getMessage());
//		}
		
		return "product-detail-page";
	}
}

package mock.project.frontend.controller;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import mock.project.frontend.LocalDateDeserializer;
import mock.project.frontend.request.OrderDTO;
import mock.project.frontend.request.ProductDTO;
import mock.project.frontend.request.UserDTO;
import mock.project.frontend.request.UserDTOReponse;
import mock.project.frontend.response.ResponseTransfer;

@Controller
public class CartController {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${order.api.url}")
	private String orderApi;
	
	@Value("${user.api.url}")
	private String userApi;
	
//	List<ProductDTO> dtos = new ArrayList<>();

	@GetMapping("/cart")
	public String viewCart(
			@CookieValue(value = "cookieProduct", defaultValue = "defaultCookieValue") String cookieProduct,
			Model model, HttpServletRequest request, HttpSession session) {
		String decodeCookieProduct = new String(Base64.getDecoder().decode(cookieProduct.getBytes()));
		GsonBuilder gsonBuilder = new GsonBuilder();
		Type productListType = new TypeToken<List<ProductDTO>>() {
		}.getType();
		List<ProductDTO> listItemCart = gsonBuilder.setPrettyPrinting()
				.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer()).create()
				.fromJson(decodeCookieProduct, productListType);
		
		String username = (String)session.getAttribute("username");
		System.out.println(username);
		String url = userApi + "/info?username="+ username;
		UserDTOReponse userInfo = restTemplate.getForObject(url, UserDTOReponse.class);
		model.addAttribute("user", userInfo);
		model.addAttribute("listItemCart", listItemCart);
		
		System.out.println("json \n" + decodeCookieProduct);
		for (ProductDTO productDTO : listItemCart) {
			System.out.println(productDTO);
		}

		for (ProductDTO productDTO : listItemCart) {
			System.out.println(productDTO.getProductId());
		}

		return "cart-page";
	}

	@PostMapping("/cart")
	public String payment(
			@RequestParam(name = "fullName") String fullName,
			@RequestParam(name = "phone") String phone, 
			@RequestParam(name = "address") String address,
			@RequestParam(name = "email") String email, Model model, @ModelAttribute("user") @Valid OrderDTO orderDTO, HttpSession session) {
		
//		System.out.println(fullName);
//		System.out.println(phone);
//		System.out.println(address);
//		System.out.println(email);
//		String username = (String)session.getAttribute("username");
////		String url = orderApi + "/cart";
////    	ResponseEntity<ResponseTransfer> response = restTemplate.postForEntity(url, orderDTO, ResponseTransfer.class);
//    	
//    	String urlOrder = orderApi + "newOrder?productId="+  + ;
//		ResponseEntity<ResponseTransfer> response = restTemplate.postForEntity(urlOrder, orderDTO, ResponseTransfer.class);
//		ResponseTransfer newOrder = response.getBody();
    	
    	
		return "cart-page";
	}
}

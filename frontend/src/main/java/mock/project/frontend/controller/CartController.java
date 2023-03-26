package mock.project.frontend.controller;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import mock.project.frontend.LocalDateDeserializer;
import mock.project.frontend.request.ProductDTO;

@Controller
public class CartController {

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/cart")
	public String viewCart(
			@CookieValue(value = "cookieProduct", defaultValue = "defaultCookieValue") String cookieProduct,
			Model model, HttpServletRequest request) {
		String decodeCookieProduct = new String(Base64.getDecoder().decode(cookieProduct.getBytes()));
		GsonBuilder gsonBuilder = new GsonBuilder();
		Type productListType = new TypeToken<List<ProductDTO>>() {}.getType();
		List<ProductDTO> listItemCart = gsonBuilder.setPrettyPrinting()
				.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer()).create()
				.fromJson(decodeCookieProduct, productListType);

		System.out.println("json /n" + decodeCookieProduct);
		for (ProductDTO productDTO : listItemCart) {
			System.out.println(productDTO);
		}

		model.addAttribute("listItemCart", listItemCart);
		for (ProductDTO productDTO : listItemCart) {
			System.out.println(productDTO);
		}

		return "cart-page";
	}
}

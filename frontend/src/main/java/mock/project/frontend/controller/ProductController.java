package mock.project.frontend.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mock.project.frontend.entities.Products;
import mock.project.frontend.request.ProductDTO;
import mock.project.frontend.request.ProductRequest;
import mock.project.frontend.response.ResponseTransfer;
import mock.project.frontend.services.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {
	
	private Logger logger = Logger.getLogger(ProductController.class);
	
	@Autowired
	private ProductService productService;
	
	//list product by categoryID
	@GetMapping(value = "/products/categories/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductDTO> findProductByBrand(@PathVariable("id") Integer categoryId,
			@RequestParam(name="page",required = false) Integer pageIndex) {
		if (pageIndex == null || pageIndex == 0) {
			Pageable pageable = PageRequest.of(0, 5, Sort.by(""+categoryId+""));
			return productService.findByBrand(pageable);
		}
		Pageable pageable = PageRequest.of(pageIndex, 5, Sort.by(""+categoryId+""));
		return productService.findByBrand(pageable);

	}
	//list all products
	@GetMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> finAllProduct(@RequestParam(name="page",required = false) Integer pageIndex) {
		if (pageIndex == null || pageIndex == 0) {
			Pageable pageable = PageRequest.of(0, 5);
			return ResponseEntity.ok(productService.findAllProduct(pageable));
		}
		Pageable pageable = PageRequest.of(pageIndex, 5);
		return ResponseEntity.ok(productService.findAllProduct(pageable));
	}
	
	//list product by search
	@GetMapping(value="/products/search",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> findProductBySearch(@RequestParam("searchField")  String searchField ){
		return ResponseEntity.ok(productService.findPoductBySearch(searchField));
	}
	
	//search product by filter
	@GetMapping(value="/products/filter",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> findProductByFilter(@RequestBody ProductRequest productRequest ){
		return ResponseEntity.ok(productService.searchProductByFilter(productRequest));
	}
	
	//find product by id 
	@GetMapping(value="/products/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductDTO> findProductById(@PathVariable("id") Integer id){
		return  ResponseEntity.ok(productService.findPoductById(id));
	}
	
	//update product by id 
	@PostMapping(value = "/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseTransfer updateProduct(@RequestBody Products product) {
		logger.info("Updating product.....");
		productService.findById(product.getProductId());
		return new ResponseTransfer("Update Successfull!");
	}
	//add new product
	@PostMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
	public Products saveProducts(@RequestBody Products product) {
		logger.info("Adding new product.....");
		return productService.save(product);
	}
	
	//list products by price ASC/DESC
	@GetMapping(value="/products/price/",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> finAllProductPriceAsc(@RequestParam("sort") String sort,@RequestParam("page")  Integer pageIndex){
		if (sort == "ASC" && pageIndex == null || pageIndex == 0 ) {
			Pageable sortedByPriceDesc =  PageRequest.of(0, 5, Sort.by("price").ascending());
			return ResponseEntity.ok(productService.findAllProduct(sortedByPriceDesc));
		}
		if (sort == "ASC" && pageIndex != 0 ) {
			Pageable sortedByPriceDesc =  PageRequest.of(pageIndex, 5, Sort.by("price").ascending());
			return ResponseEntity.ok(productService.findAllProduct(sortedByPriceDesc));
		}
		
		if (sort == "DESC" && pageIndex == null || pageIndex == 0 ) {
			Pageable sortedByPriceDesc =  PageRequest.of(0, 5, Sort.by("price").descending());
			return ResponseEntity.ok(productService.findAllProduct(sortedByPriceDesc));
		}
		
		if (sort == "DESC" && pageIndex != 0) {
			Pageable sortedByPriceDesc =  PageRequest.of(pageIndex, 5, Sort.by("price").descending());
			return ResponseEntity.ok(productService.findAllProduct(sortedByPriceDesc));
		}
		Pageable pageable = PageRequest.of(pageIndex, 5);
		return ResponseEntity.ok(productService.findAllProduct(pageable));
	}
	
}

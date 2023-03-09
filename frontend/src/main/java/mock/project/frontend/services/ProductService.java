package mock.project.frontend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mock.project.frontend.entities.Products;
import mock.project.frontend.entities.Sizes;
import mock.project.frontend.repository.ProductRepository;
import mock.project.frontend.request.ProductDTO;
import mock.project.frontend.request.ProductRequest;

@Service
@Transactional
public class ProductService {

	@Autowired
	private ProductRepository productRepo;

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private ModelMapper modelMap;

	
	public List<ProductDTO> findByBrand(Pageable pageable) {
		List<ProductDTO> productDTOs = new ArrayList<>();
		Page<Products> productByBrand = productRepo.findAll(pageable);
		for (Products product : productByBrand) {
			ProductDTO producctDTO = modelMap.map(product, ProductDTO.class);
			productDTOs.add(producctDTO);

		}
		return productDTOs;

	}

	public List<ProductDTO> findAllProduct(Pageable pageable) {
		Page<Products> products = productRepo.findAll(pageable);
		List<ProductDTO> productDTOs = new ArrayList<>();

		for (Products product : products) {
			ProductDTO producctDTO = modelMap.map(product, ProductDTO.class);
			productDTOs.add(producctDTO);
		}
		return productDTOs;

	}

	public List<ProductDTO> findPoductBySearch(String searchField) {
		List<Products> products = new ArrayList<>();
		products = productRepo.findByProductName(searchField);

		List<ProductDTO> productDTOs = new ArrayList<>();

		for (Products product : products) {
			ProductDTO producctDTO = modelMap.map(product, ProductDTO.class);
			productDTOs.add(producctDTO);
		}
		return productDTOs;

	}

	public ProductDTO findPoductById(Integer id) {
		Optional<Products> product = productRepo.findById(id);
		ProductDTO productDTO = modelMap.map(product.get(), ProductDTO.class);
		return productDTO;

	}

	public List<ProductDTO> searchProductByFilter(ProductRequest productRequest) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Products> cq = cb.createQuery(Products.class);
		Root<Products> root = cq.from(Products.class);
		List<Predicate> searchCriterias = new ArrayList<>();

		String color = productRequest.getColor();
		double size = productRequest.getSize();
		String type = productRequest.getType();
		double startRangePrice = productRequest.getStartRangePrice();
		double endRangePrice = productRequest.getEndRangePrice();

		if ((color != "") && (color != null)) {
			searchCriterias.add(cb.equal(root.get("color"), color));
		}
		if ((size != 0) && (size < 50)) {
			
			Join<Products, Sizes> productsJoin = root.join( "sizes" );
			searchCriterias.add(cb.equal( productsJoin.get("size"), size));
		}

		if ((type != "") && (type != null)) {
			searchCriterias.add(cb.equal(root.get("type"), type));
		}

		if ((startRangePrice != 0) && (endRangePrice != 0) && (startRangePrice < endRangePrice)) {
			searchCriterias.add(cb.between(root.get("price"), startRangePrice, endRangePrice));
		}

		cq.select(root).where(cb.and(searchCriterias.toArray(new Predicate[searchCriterias.size()])));

		List<Products> products = entityManager.createQuery(cq).getResultList();
		List<ProductDTO> productDTOs = new ArrayList<>();

		for (Products product : products) {
			ProductDTO producctDTO = modelMap.map(product, ProductDTO.class);
			productDTOs.add(producctDTO);

		}
		return productDTOs;
	}
	
	public Products save(Products product) {
		return productRepo.save(product);
	}
	
	public void delete(int productId) {
			productRepo.findById(productId);
		}
	
	 public ProductDTO findById(final Integer id) {
		 Optional<Products> product =  productRepo.findById(id);
		 ProductDTO producctDTO = modelMap.map(product.get(), ProductDTO.class);
			return producctDTO;
		}
}

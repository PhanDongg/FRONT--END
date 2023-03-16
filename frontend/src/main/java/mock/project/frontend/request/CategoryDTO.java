package mock.project.frontend.request;

import java.util.Set;

import mock.project.frontend.entities.Products;


public class CategoryDTO {
	
	private Integer categoryId;
	private String categoryName;
	private Set<Products> products;
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Set<Products> getProducts() {
		return products;
	}
	public void setProducts(Set<Products> products) {
		this.products = products;
	}
	
	
}

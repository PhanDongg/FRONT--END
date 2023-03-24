package mock.project.frontend.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "size")
public class Sizes {
	@Id
	@Column(name = "size_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sizeId;

	private double size;

//	@JsonIgnore
//	@ManyToMany(mappedBy = "sizes")
//	private List<Products> products;
	
	public Sizes() {
		super();
	}

	public Sizes(double size) {
		super();
		this.size = size;
	}

	public Integer getSizeId() {
		return sizeId;
	}

	public void setSizeId(Integer sizeId) {
		this.sizeId = sizeId;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

//	public List<Products> getProducts() {
//		return products;
//	}
//
//	public void setProducts(List<Products> products) {
//		this.products = products;
//	}

	@Override
	public String toString() {
		return "Sizes [sizeId=" + sizeId + ", size=" + size + "]";
	}
	
}

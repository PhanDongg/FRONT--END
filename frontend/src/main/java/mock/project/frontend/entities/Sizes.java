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
	
	@Column(name = "size")
	private int size;

	public Sizes() {
		super();
	}

	public Sizes(int size) {
		super();
		this.size = size;
	}

	public Integer getSizeId() {
		return sizeId;
	}

	public void setSizeId(Integer sizeId) {
		this.sizeId = sizeId;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "Sizes [sizeId=" + sizeId + ", size=" + size + "]";
	}
	
}

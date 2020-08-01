package com.increff.assure.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames = "product_id"))
public class InventoryPojo extends AbstractAuditable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private Long availableQuantity;
	
	@Column(nullable=false)
	private Long allocatedQuantity;
	
	@Column(nullable=false)
	private Long fulfilledQuantity;

	@OneToOne
	@JoinColumn(name="product_id", nullable=false)
	private ProductPojo product;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(Long availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	public Long getAllocatedQuantity() {
		return allocatedQuantity;
	}

	public void setAllocatedQuantity(Long allocatedQuantity) {
		this.allocatedQuantity = allocatedQuantity;
	}

	public Long getFulfilledQuantity() {
		return fulfilledQuantity;
	}

	public void setFulfilledQuantity(Long fulfilledQuantity) {
		this.fulfilledQuantity = fulfilledQuantity;
	}

	public ProductPojo getProduct() {
		return product;
	}

	public void setProduct(ProductPojo product) {
		this.product = product;
	}
	
	

}

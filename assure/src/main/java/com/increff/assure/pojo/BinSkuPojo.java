package com.increff.assure.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "assure_bin_sku", uniqueConstraints = @UniqueConstraint(columnNames = { "global_sku_id", "bin_id" }))
public class BinSkuPojo extends AbstractAuditable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "bin_id", nullable = false)
	private BinPojo bin;

	@ManyToOne
	@JoinColumn(name = "global_sku_id", nullable = false)
	private ProductPojo product;

	@Column(nullable = false)
	private Long quantity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BinPojo getBin() {
		return bin;
	}

	public void setBin(BinPojo bin) {
		this.bin = bin;
	}

	public ProductPojo getProduct() {
		return product;
	}

	public void setProduct(ProductPojo product) {
		this.product = product;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

}

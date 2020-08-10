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
@Table(name = "assure_product", uniqueConstraints = @UniqueConstraint(columnNames = { "client_id", "client_sku_id" }))
public class ProductPojo extends AbstractAuditable {

	@Id
	@Column(name = "global_sku_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long globalSkuId;

	@Column(name = "client_sku_id", nullable = false)
	private String clientSkuId;
	
	@Column(nullable = false)
	private String name;
	
	@Column(name = "brand_id", nullable = false)
	private String BrandId;
	
	@Column(nullable = false)
	private Double mrp;

	private String description;

	@ManyToOne
	@JoinColumn(name = "client_id", nullable = false)
	private ClientPojo client;

	public Long getGlobalSkuId() {
		return globalSkuId;
	}

	public void setGlobalSkuId(Long globalSkuId) {
		this.globalSkuId = globalSkuId;
	}

	public String getClientSkuId() {
		return clientSkuId;
	}

	public void setClientSkuId(String clientSkuId) {
		this.clientSkuId = clientSkuId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrandId() {
		return BrandId;
	}

	public void setBrandId(String brandId) {
		BrandId = brandId;
	}

	public Double getMrp() {
		return mrp;
	}

	public void setMrp(Double mrp) {
		this.mrp = mrp;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ClientPojo getClient() {
		return client;
	}

	public void setClient(ClientPojo client) {
		this.client = client;
	}

}

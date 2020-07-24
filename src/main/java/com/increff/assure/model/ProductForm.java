package com.increff.assure.model;

public class ProductForm {

	private String name;
	private Double mrp;
	private String description;
	private String clientSkuId;
	private Long ClientId;
	private String brandId;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getClientSkuId() {
		return clientSkuId;
	}
	public void setClientSkuId(String clientSkuId) {
		this.clientSkuId = clientSkuId;
	}
	public Long getClientId() {
		return ClientId;
	}
	public void setClientId(Long clientId) {
		ClientId = clientId;
	}
	public String getBrandId() {
		return brandId;
	}
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}
	
	
	
	
	
}

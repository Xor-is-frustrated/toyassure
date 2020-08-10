package com.increff.commons.data;

import com.increff.commons.form.BinSkuForm;

public class BinSkuData extends BinSkuForm {
	
	private Long id;
	private Long globalSkuId;
	private String productName;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getGlobalSkuId() {
		return globalSkuId;
	}
	public void setGlobalSkuId(Long globalSkuId) {
		this.globalSkuId = globalSkuId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	

}

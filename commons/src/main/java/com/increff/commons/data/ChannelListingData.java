package com.increff.commons.data;


import com.increff.commons.form.ChannelListingForm;

public class ChannelListingData extends ChannelListingForm {
	private Long id;
	private String productName;
	private Long globalSkuId;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}

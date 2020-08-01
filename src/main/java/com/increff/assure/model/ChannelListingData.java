package com.increff.assure.model;

import com.increff.assure.model.ChannelListingForm;

public class ChannelListingData extends ChannelListingForm {
	private Long id;
	private String productName;

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

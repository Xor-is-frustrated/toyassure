package com.increff.commons.data;

import com.increff.commons.form.InventoryForm;

public class InventoryData extends InventoryForm {

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

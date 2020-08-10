package com.increff.commons.form;

public class OrderItemFormCSV {

	private String clientSkuId;
	private Long orderedQuantity;
	private Double sellingPrice;
	private Long orderId;

	public Long getOrderId() { 
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getClientSkuId() {
		return clientSkuId;
	}

	public void setClientSkuId(String clientSkuId) {
		this.clientSkuId = clientSkuId;
	}

	public Long getOrderedQuantity() {
		return orderedQuantity;
	}

	public void setOrderedQuantity(Long orderedQuantity) {
		this.orderedQuantity = orderedQuantity;
	}

	public Double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(Double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

}

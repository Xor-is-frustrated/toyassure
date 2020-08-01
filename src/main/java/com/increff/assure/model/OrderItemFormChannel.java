package com.increff.assure.model;

public class OrderItemFormChannel {
	private String channelSkuId;
	private Double sellingPrice;
	private Long orderId;
	private Long orderedQuantity;

	public String getChannelSkuId() {
		return channelSkuId;
	}

	public void setChannelSkuId(String channelSkuId) {
		this.channelSkuId = channelSkuId;
	}

	public Double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(Double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getOrderedQuantity() {
		return orderedQuantity;
	}

	public void setOrderedQuantity(Long orderedQuantity) {
		this.orderedQuantity = orderedQuantity;
	}

}

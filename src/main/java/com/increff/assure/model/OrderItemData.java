package com.increff.assure.model;

public class OrderItemData extends OrderItemFormCSV {
	private Long allocatedQuantity;
	private Long fulfilledQuantity;
	private String productName;
	private Long globalSkuId;
	private String channelName;

	public String getChannelName() { 
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Long getGlobalSkuId() {
		return globalSkuId;
	}

	public void setGlobalSkuId(Long globalSkuId) {
		this.globalSkuId = globalSkuId;
	}

}

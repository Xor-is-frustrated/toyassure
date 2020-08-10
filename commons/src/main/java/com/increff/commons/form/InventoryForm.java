package com.increff.commons.form;

public class InventoryForm {

	private Long availableQuantity;
	private Long allocatedQuantity;
	private Long fulfilledQuantity;
	private Long GlobalSkuId;

	public Long getGlobalSkuId() {
		return GlobalSkuId;
	}

	public void setGlobalSkuId(Long globalSkuId) {
		GlobalSkuId = globalSkuId;
	}

	public Long getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(Long availableQuantity) {
		this.availableQuantity = availableQuantity;
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

}

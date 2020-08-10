package com.increff.commons.form;

public class BinSkuForm {

	private Long binId;
	private String clientSkuId;
	private Long quantity;
	private String clientName;
	
	public Long getBinId() {  
		return binId; 
	}
	public void setBinId(Long binId) {
		this.binId = binId;
	}
	public String getClientSkuId() {
		return clientSkuId;
	}
	public void setClientSkuId(String clientSkuId) {
		this.clientSkuId = clientSkuId;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	
	
	
}

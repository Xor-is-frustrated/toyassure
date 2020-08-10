package com.increff.commons.form;

public class OrderForm {

	private String clientName;
	private String customerName;
	private String channelOrderId;
	private String channelName;

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {       
		this.channelName = channelName;
	}

	public String getClientName() {    
		return clientName;
	}

	public void setClientName(String client) {
		this.clientName = client;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customer) {
		this.customerName = customer;
	}

	public String getChannelOrderId() {
		return channelOrderId;
	}

	public void setChannelOrderId(String channelOrderId) {
		this.channelOrderId = channelOrderId;
	}

}

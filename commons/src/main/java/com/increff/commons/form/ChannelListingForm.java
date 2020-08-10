package com.increff.commons.form;

public class ChannelListingForm {

	private String channelName;
	private String clientName;
	private String clientSkuId;
	private String channelSkuId;
	private Long channelId;

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientSkuId() {
		return clientSkuId;
	}

	public void setClientSkuId(String clientSkuId) {
		this.clientSkuId = clientSkuId;
	}

	public String getChannelSkuId() {
		return channelSkuId;
	}

	public void setChannelSkuId(String channelSkuId) {
		this.channelSkuId = channelSkuId;
	}

}

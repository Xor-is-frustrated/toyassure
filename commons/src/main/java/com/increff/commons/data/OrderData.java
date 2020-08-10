package com.increff.commons.data;

import com.increff.commons.enums.OrderStatus;
import com.increff.commons.form.OrderForm;

import java.time.ZonedDateTime;



public class OrderData extends OrderForm {
	private Long id;
	private OrderStatus status;
	private Long channelId;
	private ZonedDateTime createdDate;

	public ZonedDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(ZonedDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}

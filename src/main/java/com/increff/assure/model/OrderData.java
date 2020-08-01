package com.increff.assure.model;

import com.increff.assure.pojo.OrderStatus;

public class OrderData extends OrderForm {
	private Long id;
	private OrderStatus status;

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

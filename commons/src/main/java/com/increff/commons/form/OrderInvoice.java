package com.increff.commons.form;

import com.increff.commons.data.OrderItemData;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@XmlRootElement(name = "Invoice")
public class OrderInvoice { 

	private double sellingprice;
	private String orderDate;
	private Long id;
	private String orderId;
	private List<OrderItemData> items;
	private String channelName;

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public double getSellingprice() {
		return sellingprice;
	}

	public void setSellingprice(double sellingprice) {
		this.sellingprice = sellingprice;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<OrderItemData> getItems() {
		return items;
	}

	public void setItems(List<OrderItemData> items) {
		this.items = items;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

}

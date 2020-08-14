package com.increff.commons.form;

import com.increff.commons.data.OrderItemData;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Getter
@Setter
@XmlRootElement(name = "Invoice")
public class OrderInvoice { 

	private double sellingprice;
	private String orderDate;
	private Long id;
	private String orderId;
	private List<OrderItemData> items;
	private String channelName;
	private String client;
	private String customer;



}

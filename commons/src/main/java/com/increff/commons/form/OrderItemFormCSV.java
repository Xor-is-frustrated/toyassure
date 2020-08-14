package com.increff.commons.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemFormCSV {

	private String clientSkuId;
	private Long orderedQuantity;
	private Double sellingPrice;
	private Long orderId;



}

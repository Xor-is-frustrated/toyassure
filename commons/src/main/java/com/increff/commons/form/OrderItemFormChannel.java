package com.increff.commons.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemFormChannel {
	private String channelSkuId;
	private Double sellingPrice;
	private Long orderId;
	private Long orderedQuantity;



}

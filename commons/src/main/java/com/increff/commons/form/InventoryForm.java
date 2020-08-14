package com.increff.commons.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryForm {

	private Long availableQuantity;
	private Long allocatedQuantity;
	private Long fulfilledQuantity;
	private Long GlobalSkuId;



}

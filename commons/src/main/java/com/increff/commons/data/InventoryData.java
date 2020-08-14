package com.increff.commons.data;

import com.increff.commons.form.InventoryForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryData extends InventoryForm {

	private Long id;
	private String productName;
	private String clientName;
	private String clientSkuId;



}

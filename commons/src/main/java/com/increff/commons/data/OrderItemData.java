package com.increff.commons.data;


import com.increff.commons.form.OrderItemFormCSV;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemData extends OrderItemFormCSV {
	private Long allocatedQuantity;
	private Long fulfilledQuantity;
	private String productName;
	private Long globalSkuId;
	private String channelName;
	private Long id;
	private double itemTotalCost;

	public double getItemTotalCost() {
		return itemTotalCost;
	}

	public void setItemTotalCost(double itemTotalCost) {
		this.itemTotalCost = itemTotalCost;
	}


}

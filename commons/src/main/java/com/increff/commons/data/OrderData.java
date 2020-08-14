package com.increff.commons.data;

import com.increff.commons.enums.OrderStatus;
import com.increff.commons.form.OrderForm;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;


@Getter
@Setter
public class OrderData extends OrderForm {
	private Long id;
	private OrderStatus status;
	private Long channelId;
	private ZonedDateTime createdDate;



}

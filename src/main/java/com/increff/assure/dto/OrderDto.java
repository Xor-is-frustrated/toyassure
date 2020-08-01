package com.increff.assure.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.increff.assure.model.OrderData;
import com.increff.assure.model.OrderForm;
import com.increff.assure.pojo.ChannelPojo;
import com.increff.assure.pojo.ClientPojo;
import com.increff.assure.pojo.OrderPojo;
import com.increff.assure.service.ApiException;
import com.increff.assure.service.ChannelService;
import com.increff.assure.service.ClientService;
import com.increff.assure.service.OrderService;
import com.increff.assure.util.ConvertorUtil;

@Service
public class OrderDto {

	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private ChannelService channelService; 

	public OrderData add(OrderForm form) throws ApiException {
		ClientPojo client= clientService.getByName(form.getClientName());	
		ClientPojo customer = clientService.getByName(form.getCustomerName());
		ChannelPojo channel= channelService.getByName(form.getChannelName());
		OrderPojo pojo = ConvertorUtil.convert(form,channel,client,customer);
		OrderPojo order = orderService.add(pojo);
		return ConvertorUtil.convert(order);
	}

	public OrderData get(Long id) throws ApiException {
		OrderPojo pojo = orderService.get(id);
		return ConvertorUtil.convert(pojo);
	}

	public List<OrderData> getAll() {
		List<OrderPojo> list = orderService.getAll();
		return ConvertorUtil.convertOrders(list);
	}

	public List<OrderData> getInternalOrders() {
		List<OrderPojo>orders=orderService.getInternalOrders();
		return ConvertorUtil.convertOrders(orders);
	}
	
}

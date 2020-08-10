package com.increff.assure.dto;

import com.increff.assure.channel.Channel;
import com.increff.assure.pojo.ClientPojo;
import com.increff.assure.pojo.OrderItemPojo;
import com.increff.assure.pojo.OrderPojo;
import com.increff.assure.service.ApiException;
import com.increff.assure.service.ClientService;
import com.increff.assure.service.OrderItemService;
import com.increff.assure.service.OrderService;
import com.increff.assure.util.ConvertorUtil;
import com.increff.commons.data.ChannelData;
import com.increff.commons.data.OrderData;
import com.increff.commons.form.OrderForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDto {

	@Autowired
	private OrderService orderService;

	@Autowired
	private ClientService clientService;

	@Autowired
	private Channel channelService;

	@Autowired
	private OrderItemService orderItemService;

	public OrderData add(OrderForm form) throws ApiException {
		ClientPojo client = clientService.getByName(form.getClientName());
		ClientPojo customer = clientService.getByName(form.getCustomerName());
		ChannelData channel = channelService.getByName(form.getChannelName());
		OrderPojo pojo = ConvertorUtil.convert(form, channel, client, customer);
		OrderPojo order = orderService.add(pojo);
		return ConvertorUtil.convert(order,form.getChannelName());
	}

	public OrderData get(Long id) throws ApiException {
		OrderPojo pojo = orderService.get(id);
		ChannelData channel = channelService.selectChannel(pojo.getChannelId());
		return ConvertorUtil.convert(pojo,channel.getName());
	}

	public List<OrderData> getAll() {
		List<OrderPojo> list = orderService.getAll();
		List<String>channelNames=new ArrayList<String>();
		for(OrderPojo pojo:list){
			channelNames.add(channelService.selectChannel(pojo.getChannelId()).getName());
		}
		return ConvertorUtil.convertOrders(list,channelNames);
	}

	public List<OrderData> getInternalOrders() {
		List<OrderPojo> orders = orderService.getInternalOrders();
		List<String>channelNames=new ArrayList<String>();
		for(OrderPojo pojo:orders){
			channelNames.add(channelService.selectChannel(pojo.getChannelId()).getName());
		}
		return ConvertorUtil.convertOrders(orders,channelNames);

	}

	public List<OrderData> getExternalOrders() {
		List<OrderPojo> orders = orderService.getExternalOrders();
		List<String>channelNames=new ArrayList<String>();
		for(OrderPojo pojo:orders){
			channelNames.add(channelService.selectChannel(pojo.getChannelId()).getName());
		}
		return ConvertorUtil.convertOrders(orders,channelNames);

	}

	public void delete(Long id) throws ApiException {
		OrderPojo order=  orderService.get(id);
		List<OrderItemPojo> list = orderItemService.getByOrder(order);
		for(OrderItemPojo item:list) {
			orderItemService.delete(item.getId());
		}
		orderService.delete(order.getId());

	}

}

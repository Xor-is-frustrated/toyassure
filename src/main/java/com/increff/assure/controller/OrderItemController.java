package com.increff.assure.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.increff.assure.dto.OrderItemDto;
import com.increff.assure.model.OrderItemData;
import com.increff.assure.model.OrderItemFormCSV;
import com.increff.assure.model.OrderItemFormChannel;
import com.increff.assure.service.ApiException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping("/api/orderitem")
public class OrderItemController {   

	@Autowired
	private OrderItemDto dto;

	@ApiOperation(value = "Adds an orderItem through assure")
	@RequestMapping(path = "/assure", method = RequestMethod.POST)
	public void addByAssure(@RequestBody OrderItemFormCSV form) throws ApiException {
		dto.addByAssure(form);
	}

	@ApiOperation(value = "Adds an orderItem through channel")
	@RequestMapping(path = "/channel", method = RequestMethod.POST)
	public void addByChannel(@RequestBody OrderItemFormChannel form) throws ApiException {
		dto.addByChannel(form);
	}

	@ApiOperation(value = "Gets an orderItem by id")
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public OrderItemData get(@PathVariable Long id) throws ApiException {
		return dto.get(id);
	}
	
	@ApiOperation(value = "Gets orderItems list by orderid")
	@RequestMapping(path = "/order/{id}", method = RequestMethod.GET)
	public List<OrderItemData> getByOrder(@PathVariable Long id) throws ApiException {
		return dto.getByOrder(id);
	}
	
	@ApiOperation(value = "Allocates all order items of an order id")
	@RequestMapping(path = "/allocate/{id}", method = RequestMethod.GET)
	public List<OrderItemData> allocate(@PathVariable Long id) throws ApiException {
		return dto.allocate(id);

	}
	
	@ApiOperation(value = "Fulfills all order items of an order id")
	@RequestMapping(path = "/fulfill/{id}", method = RequestMethod.GET)
	public List<OrderItemData> fulfill(@PathVariable Long id) throws ApiException {
		return dto.fulfillAndGenerateInvoice(id);

	}

}

package com.increff.assure.controller;

import java.util.List;

import com.increff.commons.data.OrderData;
import com.increff.commons.form.OrderForm;
import com.increff.commons.form.OrderSearchForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.increff.assure.dto.OrderDto;
import com.increff.assure.service.ApiException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping("/api/order")
public class OrderController {

	@Autowired
	private OrderDto dto;

	@ApiOperation(value = "Adds a order")
	@RequestMapping(path = "", method = RequestMethod.POST)
	public OrderData add(@RequestBody OrderForm form) throws ApiException {
		return dto.add(form);
	}
	
	@ApiOperation(value = "Deletes a order")
	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) throws ApiException {
		 dto.delete(id);
	}

	@ApiOperation(value = "Gets a order by id")
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public OrderData get(@PathVariable int id) throws ApiException {

		return dto.get(Long.valueOf(id));
	}

	@ApiOperation(value = "Gets a order by id")
	@RequestMapping(path = "/name/{name}", method = RequestMethod.GET)
	public OrderData getByOrderId(@PathVariable String name) throws ApiException {

		return dto.getByOrderId(name);
	}


	@ApiOperation(value = "Gets list of all orders")
	@RequestMapping(method = RequestMethod.GET)
	public List<OrderData> getAll() throws ApiException {
		return dto.getAll();

	}

	@ApiOperation(value = "Gets list of all channel orders")
	@RequestMapping(path="/external" ,method = RequestMethod.GET)
	public List<OrderData> getChannelOrders() throws ApiException {
		return dto.getExternalOrders();

	}



	@ApiOperation(value = "Gets list of all channel orders")
	@RequestMapping(path="/search" ,method = RequestMethod.POST)
	public List<OrderData> getChannelOrdersBySearch(@RequestBody OrderSearchForm form) throws ApiException {
		return dto.getOrderBySearch(form);
	}

	@ApiOperation(value = "Gets list of all channel orders")
	@RequestMapping(path="/searchexternal" ,method = RequestMethod.POST)
	public List<OrderData> getChannelOrdersBySearchex(@RequestBody OrderSearchForm form) throws ApiException {
		return dto.getOrderBySearchExternal(form);
	}



}

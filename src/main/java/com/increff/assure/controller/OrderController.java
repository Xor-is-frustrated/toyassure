package com.increff.assure.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.increff.assure.dto.OrderDto;
import com.increff.assure.model.OrderData;
import com.increff.assure.model.OrderForm;
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

	@ApiOperation(value = "Gets a order by id")
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public OrderData get(@PathVariable int id) throws ApiException {

		return dto.get(Long.valueOf(id));
	}

	@ApiOperation(value = "Gets list of all orders")
	@RequestMapping(method = RequestMethod.GET)
	public List<OrderData> getAll() {
		return dto.getAll();

	}
	
	@ApiOperation(value = "Gets list of all internal channel orders")
	@RequestMapping(path="/internal" ,method = RequestMethod.GET)
	public List<OrderData> getAssureOrders() {
		return dto.getInternalOrders();

	}

}

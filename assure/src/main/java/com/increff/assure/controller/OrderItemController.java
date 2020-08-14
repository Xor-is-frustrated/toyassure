package com.increff.assure.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;

import com.increff.assure.service.ApiException;
import com.increff.commons.data.OrderItemData;
import com.increff.commons.form.OrderItemFormCSV;
import com.increff.commons.form.OrderItemFormChannel;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.increff.assure.dto.OrderItemDto;

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
	public OrderItemData addByChannel(@RequestBody OrderItemFormChannel form) throws ApiException {
		return dto.addByChannel(form);
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

	
	@ApiOperation(value = "Updates an item")
	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	public void update(@PathVariable Long id, @RequestBody OrderItemFormChannel form) throws ApiException {
		dto.update(id, form);
	}
	
	@ApiOperation(value = "Deletes an item")
	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) throws ApiException {
		dto.delete(id);
	}
	
	@ApiOperation(value = "Allocates all order items of an order id")
	@RequestMapping(path = "/allocate/{id}", method = RequestMethod.GET)
	public List<OrderItemData> allocate(@PathVariable Long id) throws ApiException {
		return dto.allocate(id);

	}

	@ApiOperation(value = "Allocates all order items of an order id")
	@RequestMapping(path = "/fulfill/{id}", method = RequestMethod.GET)
	public void fulfill(@PathVariable Long id) throws ApiException {
		dto.fulfill(id);

	}

	@ApiOperation(value = "Fulfills all order items of an order id")
	@RequestMapping(path = "/invoice/{id}", method = RequestMethod.GET)
	public void invoice(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response)
			throws ApiException, JAXBException, IOException {
		dto.generateInvoice(id, response);

	}

}

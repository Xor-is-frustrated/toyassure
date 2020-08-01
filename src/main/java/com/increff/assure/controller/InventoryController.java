package com.increff.assure.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.increff.assure.dto.InventoryDto;
import com.increff.assure.model.InventoryData;
import com.increff.assure.model.InventoryForm;
import com.increff.assure.service.ApiException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

	@Autowired
	private InventoryDto dto;

	@ApiOperation(value = "Gets an inventory item by id")
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public InventoryData get(@PathVariable Long id) throws ApiException {
		return dto.get(id);
	}

	@ApiOperation(value = "Gets list of all inventory")
	@RequestMapping(method = RequestMethod.GET)
	public List<InventoryData> getAll() {
		return dto.getAll();
	}

	@ApiOperation(value = "Updates an inventory")
	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	public void update(@PathVariable Long id, @RequestBody InventoryForm form) throws ApiException {
		dto.update(id, form);
	}

}

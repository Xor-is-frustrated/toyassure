package com.increff.assure.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.increff.assure.dto.BinSkuDto;
import com.increff.assure.model.BinSkuData;
import com.increff.assure.model.BinSkuForm;
import com.increff.assure.service.ApiException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping("/api/binsku")
public class BinSkuController {
	
	@Autowired
	private BinSkuDto binSkuDto;
    
	@ApiOperation(value = "Adds a binsku")
	@RequestMapping(path = "", method = RequestMethod.POST)
	public void add(@RequestBody BinSkuForm form) throws ApiException {
		binSkuDto.add(form);
	}

	@ApiOperation(value = "Gets a binsku by id")
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public BinSkuData get(@PathVariable int id) throws ApiException {
		return binSkuDto.get(Long.valueOf(id));
	}

	@ApiOperation(value = "Gets list of all binskus")
	@RequestMapping(method = RequestMethod.GET)
	public List<BinSkuData> getAll() {
		return binSkuDto.getAll();

	}
	
	@ApiOperation(value = "Updates a binsku")
	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	public void update(@PathVariable int id, @RequestBody Long quantity) throws ApiException {
		binSkuDto.update(Long.valueOf(id),quantity);

	}


}

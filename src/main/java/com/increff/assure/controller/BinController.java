package com.increff.assure.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.increff.assure.dto.BinDto;
import com.increff.assure.model.BinData;
import com.increff.assure.service.ApiException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping("/api/bin")
public class BinController {
	
	@Autowired
	private BinDto dto;

	@ApiOperation(value = "Adds a bin")
	@RequestMapping(path = "", method = RequestMethod.POST)
	public void add() throws ApiException {
		dto.add();
	}
  
	@ApiOperation(value = "Gets a bin by id")
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public BinData get(@PathVariable int id) throws ApiException {
		
		return dto.get(Long.valueOf(id));
	}

	@ApiOperation(value = "Gets list of all bins")
	@RequestMapping(method = RequestMethod.GET)
	public List<BinData> getAll() {
		return dto.getAll();

	}

}

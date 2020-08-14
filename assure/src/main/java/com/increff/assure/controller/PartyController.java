package com.increff.assure.controller;

import java.util.List;

import javax.validation.Valid;

import com.increff.commons.data.PartyData;
import com.increff.commons.form.PartyForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.increff.assure.dto.PartyDto;
import com.increff.assure.service.ApiException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping("/api/client")
public class PartyController {
	
	@Autowired
	private PartyDto dto;

	@ApiOperation(value = "Adds a client")
	@RequestMapping(path = "", method = RequestMethod.POST)
	public void add(@RequestBody @Valid PartyForm form) throws ApiException {
		dto.add(form);
	}
  
	@ApiOperation(value = "Gets a client by id")
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public PartyData get(@PathVariable int id) throws ApiException {
		
		return dto.get(Long.valueOf(id));
	}

	@ApiOperation(value = "Gets a client by id")
	@RequestMapping(path = "/name/{name}", method = RequestMethod.GET)
	public PartyData getByName(@PathVariable String name) throws ApiException {

		return dto.getByName(name);
	}

	@ApiOperation(value = "Gets list of all clients")
	@RequestMapping(method = RequestMethod.GET)
	public List<PartyData> getAll() {
		return dto.getAll();

	}

	@ApiOperation(value = "Gets list of all clients")
	@RequestMapping(path = "/clients",method = RequestMethod.GET)
	public List<PartyData> getAllClients() {
		return dto.getAllClients();

	}

	@ApiOperation(value = "Gets list of all clients")
	@RequestMapping(path = "/customers",method = RequestMethod.GET)
	public List<PartyData> getAllCustomers() {
		return dto.getAllCustomers();

	}


	
	@ApiOperation(value = "Updates a client")
	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	public void update(@PathVariable Long id, @RequestBody PartyForm form) throws ApiException {
		dto.update(id, form);
	}

}

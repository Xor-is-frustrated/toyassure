package com.increff.assure.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.increff.assure.dto.ClientDto;
import com.increff.assure.model.ClientData;
import com.increff.assure.model.ClientForm;
import com.increff.assure.service.ApiException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping("/api/client")
public class ClientController {
	
	@Autowired
	private ClientDto dto;

	@ApiOperation(value = "Adds a client")
	@RequestMapping(path = "", method = RequestMethod.POST)
	public void add(@RequestBody @Valid ClientForm form) throws ApiException {
		dto.add(form);
	}
  
	@ApiOperation(value = "Gets a client by id")
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ClientData get(@PathVariable int id) throws ApiException {
		
		return dto.get(Long.valueOf(id));
	}

	@ApiOperation(value = "Gets list of all clients")
	@RequestMapping(method = RequestMethod.GET)
	public List<ClientData> getAll() {
		return dto.getAll();

	}
	
	@ApiOperation(value = "Updates a client")
	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	public void update(@PathVariable Long id, @RequestBody ClientForm form) throws ApiException {
		dto.update(id, form);
	}

}

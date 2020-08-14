package com.increff.assure.controller;

import com.increff.assure.dto.ChannelDto;
import com.increff.assure.service.ApiException;
import com.increff.commons.data.ChannelData;
import com.increff.commons.data.PartyData;
import com.increff.commons.form.ChannelForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api
@RestController
@RequestMapping("/api/channel")
public class ChannelController {
	
	@Autowired
	private ChannelDto dto;
	
	@ApiOperation(value = "Adds a channel")
	@RequestMapping(path = "", method = RequestMethod.POST)
	public void add(@RequestBody @Valid ChannelForm form) throws ApiException {
		dto.add(form);
	}
  
	@ApiOperation(value = "Gets a channel by id")
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ChannelData get(@PathVariable int id) throws ApiException {
		return dto.get(Long.valueOf(id));
	}

	@ApiOperation(value = "Gets a channel by name")
	@RequestMapping(path = "/name/{name}", method = RequestMethod.GET)
	public ChannelData getByName(@PathVariable String name) throws ApiException {
		return dto.getByName(name);
	}

	@ApiOperation(value = "Gets list of all channels")
	@RequestMapping(method = RequestMethod.GET)
	public List<ChannelData> getAll() {
		return dto.getAll();

	}
	
	@ApiOperation(value = "Updates a channel")
	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	public void update(@PathVariable Long id, @RequestBody ChannelForm form) throws ApiException {
		dto.update(id, form);
	}

	@ApiOperation(value = "Gets list of all clients")
	@RequestMapping(path = "/self",method = RequestMethod.GET)
	public List<ChannelData> getAllSelf() {
		return dto.getAllSelf();

	}

	@ApiOperation(value = "Gets list of all clients")
	@RequestMapping(path = "/channel",method = RequestMethod.GET)
	public List<ChannelData> getAllChannel() {
		return dto.getAllCustomers();

	}


}

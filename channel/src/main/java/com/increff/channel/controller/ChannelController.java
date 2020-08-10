package com.increff.channel.controller;

import java.util.List;

import javax.validation.Valid;

import com.increff.commons.data.ChannelData;
import com.increff.commons.form.ChannelForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.increff.channel.dto.ChannelDto;
import com.increff.channel.service.ApiException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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

	@ApiOperation(value = "Gets a channel by id")
	@RequestMapping(path = "/name/{name}", method = RequestMethod.GET)
	public ChannelData get(@PathVariable String name) throws ApiException {
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

}
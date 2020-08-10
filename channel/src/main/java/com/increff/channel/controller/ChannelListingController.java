package com.increff.channel.controller;

import java.util.List;

import javax.validation.Valid;

import com.increff.commons.data.ChannelListingData;
import com.increff.commons.form.ChannelListingForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.increff.channel.dto.ChannelListingDto;
import com.increff.channel.service.ApiException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping("/api/channellisting")
public class ChannelListingController {

	@Autowired
	private ChannelListingDto dto;

	@ApiOperation(value = "Adds a channelListing")
	@RequestMapping(path = "", method = RequestMethod.POST)
	public void add(@RequestBody ChannelListingForm form) throws ApiException {
		dto.add(form);
	}
  
	@ApiOperation(value = "Gets a channelListing by id")
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ChannelListingData get(@PathVariable int id) throws ApiException {
		return dto.get(Long.valueOf(id));
	}

	@ApiOperation(value = "Gets a channelListing by id")
	@RequestMapping(path = "/channelidandsku", method = RequestMethod.POST)
	public ChannelListingData getByChannelNameAndSku(@RequestBody ChannelListingForm form) throws ApiException {
		return dto.getByChannelIdAndSku(form);
	}

	@ApiOperation(value = "Gets list of all channelListings")
	@RequestMapping(method = RequestMethod.GET)
	public List<ChannelListingData> getAll() {
		return dto.getAll();

	}


	
}

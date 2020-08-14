package com.increff.assure.dto;


import com.increff.assure.pojo.ChannelPojo;
import com.increff.assure.service.AbstractUnitTest;

import com.increff.assure.service.ApiException;
import com.increff.assure.service.ChannelService;
import com.increff.commons.data.ChannelData;
import com.increff.commons.enums.InvoiceType;
import com.increff.commons.form.ChannelForm;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ChannelDtoTest extends AbstractUnitTest {

	@Autowired
	private ChannelDto channelDto;

	@Autowired
	private ChannelService channelService;

	@Test
	public void testAdd() throws ApiException {
		ChannelForm channel = new ChannelForm();
		channel.setName("channel");
		channel.setType(InvoiceType.CHANNEL);

		ChannelData list = channelDto.add(channel);

	}

	@Test
	public void testGet() throws ApiException {
		ChannelPojo channel = new ChannelPojo();
		channel.setName("channel");
		channel.setType(InvoiceType.CHANNEL);

		channelService.add(channel);
		ChannelPojo channel1 = new ChannelPojo();
		channel1.setName("channel1");
		channel1.setType(InvoiceType.CHANNEL);

		channelService.add(channel1);

		ChannelData list = channelDto.get(channel.getId());
		assertEquals(channel.getName(), list.getName());
		assertEquals(channel.getType(), list.getType());
	}

	@Test
	public void testGetByName() throws ApiException {
		ChannelPojo channel = new ChannelPojo();
		channel.setName("channel");
		channel.setType(InvoiceType.CHANNEL);

		channelService.add(channel);
		ChannelPojo channel1 = new ChannelPojo();
		channel1.setName("channel1");
		channel1.setType(InvoiceType.CHANNEL);

		channelService.add(channel1);

		ChannelData list = channelDto.getByName(channel.getName());
		assertEquals(channel.getName(), list.getName());
		assertEquals(channel.getType(), list.getType());
	}

	public void testGetAll() throws ApiException {
		ChannelPojo channel = new ChannelPojo();
		channel.setName("channel");
		channel.setType(InvoiceType.CHANNEL);

		channelService.add(channel);
		ChannelPojo channel1 = new ChannelPojo();
		channel1.setName("channel1");
		channel1.setType(InvoiceType.CHANNEL);

		channelService.add(channel1);

		List<ChannelData> list = channelDto.getAll();

		assertEquals(2, list.size());
		assertEquals(channel.getName(), list.get(0).getName());
		assertEquals(channel.getType(), list.get(0).getType());

		assertEquals(channel1.getName(), list.get(1).getName());
		assertEquals(channel1.getType(), list.get(1).getType());
	}

}

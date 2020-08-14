package com.increff.assure.service;

import com.increff.assure.pojo.ChannelPojo;
import com.increff.commons.enums.InvoiceType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ChannelServiceTest extends AbstractUnitTest {
	@Autowired
	private ChannelService channelService;

	@Test 
	public void testAdd() throws ApiException {
		ChannelPojo channel = new ChannelPojo();
		channel.setName("channel");
		channel.setType(InvoiceType.CHANNEL);

		channelService.add(channel);
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

		ChannelPojo list = channelService.get(channel.getId());
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

		ChannelPojo list = channelService.getByName(channel.getName());
		assertEquals(channel.getName(), list.getName());
		assertEquals(channel.getType(), list.getType());
	}

	@Test
	public void testGetAll() throws ApiException {
		ChannelPojo channel = new ChannelPojo();
		channel.setName("channel");
		channel.setType(InvoiceType.CHANNEL);

		channelService.add(channel);
		ChannelPojo channel1 = new ChannelPojo();
		channel1.setName("channel1");
		channel1.setType(InvoiceType.CHANNEL);

		channelService.add(channel1);

		List<ChannelPojo> list = channelService.getAll();

		assertEquals(3, list.size());
		assertEquals(channel.getName(), list.get(1).getName());
		assertEquals(channel.getType(), list.get(1).getType());

		assertEquals(channel1.getName(), list.get(2).getName());
		assertEquals(channel1.getType(), list.get(2).getType());

	}

}

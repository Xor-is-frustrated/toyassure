package com.increff.channel.service;

import static org.junit.Assert.assertEquals;

import java.util.List;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.increff.channel.pojo.ChannelListingPojo;
import com.increff.channel.pojo.ChannelPojo;
import com.increff.commons.enums.ChannelType;

import com.increff.commons.enums.ClientType;


public class ChannelListingServiceTest extends AbstractUnitTest {

	@Test
	public void test(){

	}

//	@Autowired
//	private ClientService clientService;
//
//	@Autowired
//	private ProductService productService;
//
//	@Autowired
//	private ChannelService channelService;
//
//	@Autowired
//	private ChannelListingService channelListingService;
//
//	@Test
//	public void testAdd() throws ApiException {
//		ClientPojo client = new ClientPojo();
//		client.setName("assure");
//		client.setType(ClientType.CLIENT);
//
//		clientService.add(client);
//
//		ProductPojo c = new ProductPojo();
//		c.setName("assure");
//		c.setBrandId("brand");
//		c.setClientSkuId("clientsku");
//		c.setDescription("this is description");
//		c.setMrp(1.1);
//		c.setClient(client);
//
//		productService.add(c);
//
//		ChannelPojo channel = new ChannelPojo();
//		channel.setName("channel");
//		channel.setType(ChannelType.CHANNEL);
//
//		channelService.add(channel);
//
//		ChannelListingPojo cl = new ChannelListingPojo();
//		cl.setChannel(channel);
//		cl.setClient(client);
//		cl.setProduct(c);
//		cl.setChannelSkuId("channelsku");
//
//		channelListingService.add(cl);
//
//	}
//
//	@Test
//	public void testSelect() throws ApiException {
//		ClientPojo client = new ClientPojo();
//		client.setName("assure");
//		client.setType(ClientType.CLIENT);
//
//		clientService.add(client);
//
//		ProductPojo c = new ProductPojo();
//		c.setName("assure");
//		c.setBrandId("brand");
//		c.setClientSkuId("clientsku");
//		c.setDescription("this is description");
//		c.setMrp(1.1);
//		c.setClient(client);
//
//		productService.add(c);
//
//		ChannelPojo channel = new ChannelPojo();
//		channel.setName("channel");
//		channel.setType(ChannelType.CHANNEL);
//
//		channelService.add(channel);
//
//		ChannelListingPojo cl = new ChannelListingPojo();
//		cl.setChannel(channel);
//		cl.setClient(client);
//		cl.setProduct(c);
//		cl.setChannelSkuId("channelsku");
//
//		channelListingService.add(cl);
//
//		ChannelListingPojo list = channelListingService.get(cl.getId());
//		assertEquals(cl.getChannelSkuId(), list.getChannelSkuId());
//		assertEquals(cl.getProduct(), list.getProduct());
//		assertEquals(cl.getClient(), list.getClient());
//		assertEquals(cl.getChannel(), list.getChannel());
//
//	}
//
//	@Test
//	public void testSelectAll() throws ApiException {
//		ClientPojo client = new ClientPojo();
//		client.setName("assure");
//		client.setType(ClientType.CLIENT);
//
//		clientService.add(client);
//
//		ProductPojo c = new ProductPojo();
//		c.setName("assure");
//		c.setBrandId("brand");
//		c.setClientSkuId("clientsku");
//		c.setDescription("this is description");
//		c.setMrp(1.1);
//		c.setClient(client);
//
//		productService.add(c);
//
//		ChannelPojo channel = new ChannelPojo();
//		channel.setName("channel");
//		channel.setType(ChannelType.CHANNEL);
//
//		channelService.add(channel);
//
//		ChannelListingPojo cl = new ChannelListingPojo();
//		cl.setChannel(channel);
//		cl.setClient(client);
//		cl.setProduct(c);
//		cl.setChannelSkuId("channelsku");
//
//		channelListingService.add(cl);
//
//		ChannelListingPojo cl1 = new ChannelListingPojo();
//		cl1.setChannel(channel);
//		cl1.setClient(client);
//		cl1.setProduct(c);
//		cl1.setChannelSkuId("channelsku1");
//
//		channelListingService.add(cl1);
//
//		List<ChannelListingPojo> list = channelListingService.getAll();
//		assertEquals(2, list.size());
//		assertEquals(cl.getChannelSkuId(), list.get(0).getChannelSkuId());
//		assertEquals(cl.getProduct(), list.get(0).getProduct());
//		assertEquals(cl.getClient(), list.get(0).getClient());
//		assertEquals(cl.getChannel(), list.get(0).getChannel());
//
//		assertEquals(cl1.getChannelSkuId(), list.get(1).getChannelSkuId());
//		assertEquals(cl1.getProduct(), list.get(1).getProduct());
//		assertEquals(cl1.getClient(), list.get(1).getClient());
//		assertEquals(cl1.getChannel(), list.get(1).getChannel());
//	}
//
//	@Test
//	public void testSelectByParams() throws ApiException {
//		ClientPojo client = new ClientPojo();
//		client.setName("assure");
//		client.setType(ClientType.CLIENT);
//
//		clientService.add(client);
//
//		ProductPojo c = new ProductPojo();
//		c.setName("assure");
//		c.setBrandId("brand");
//		c.setClientSkuId("clientsku");
//		c.setDescription("this is description");
//		c.setMrp(1.1);
//		c.setClient(client);
//
//		productService.add(c);
//
//		ChannelPojo channel = new ChannelPojo();
//		channel.setName("channel");
//		channel.setType(ChannelType.CHANNEL);
//
//		channelService.add(channel);
//
//		ChannelListingPojo cl = new ChannelListingPojo();
//		cl.setChannel(channel);
//		cl.setClient(client);
//		cl.setProduct(c);
//		cl.setChannelSkuId("channelsku");
//
//		channelListingService.add(cl);
//
//		ChannelListingPojo cl1 = new ChannelListingPojo();
//		cl1.setChannel(channel);
//		cl1.setClient(client);
//		cl1.setProduct(c);
//		cl1.setChannelSkuId("channelsku1");
//
//		channelListingService.add(cl1);
//		ChannelListingPojo list = channelListingService.getByChannelSkuIdAndChannel(
//				"channelsku", channel);
//		assertEquals(cl.getChannelSkuId(), list.getChannelSkuId());
//		assertEquals(cl.getProduct(), list.getProduct());
//		assertEquals(cl.getClient(), list.getClient());
//		assertEquals(cl.getChannel(), list.getChannel());
//
//	}

}

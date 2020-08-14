package com.increff.assure.service;

import static org.junit.Assert.assertEquals;

import com.increff.assure.dao.ChannelDao;
import com.increff.assure.pojo.ChannelPojo;
import com.increff.assure.pojo.OrderPojo;
import com.increff.assure.pojo.PartyPojo;
import com.increff.commons.enums.InvoiceType;
import com.increff.commons.enums.OrderStatus;
import com.increff.commons.enums.PartyType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


import com.increff.assure.dao.PartyDao;

import java.util.List;

public class OrderServiceTest extends AbstractUnitTest {

	@Autowired
	private PartyDao partyDao;

	@Autowired
	private ChannelDao channelDao;
	
	@Autowired
	private OrderService orderService;

	@Test
	public void testAdd() throws ApiException {
		PartyPojo Party = new PartyPojo();
		Party.setName("assure");
		Party.setType(PartyType.CLIENT);

		partyDao.insert(Party);

		PartyPojo customer = new PartyPojo();
		customer.setName("customer");
		customer.setType(PartyType.CUSTOMER);

		partyDao.insert(customer);

		ChannelPojo channel = new ChannelPojo();
		channel.setName("channel");
		channel.setType(InvoiceType.CHANNEL);

		channelDao.insert(channel);

		OrderPojo order = new OrderPojo();
		order.setChannelId(channel.getId());
		order.setClient(Party);
		order.setCustomer(customer);
		order.setStatus(OrderStatus.CREATED);
		order.setChannelOrderId("channelorderid");

		orderService.add(order);

	}

	@Test
	public void testSelect() throws ApiException {
		PartyPojo Party = new PartyPojo();
		Party.setName("assure");
		Party.setType(PartyType.CLIENT);

		partyDao.insert(Party);

		PartyPojo customer = new PartyPojo();
		customer.setName("customer");
		customer.setType(PartyType.CUSTOMER);

		partyDao.insert(customer);

		ChannelPojo channel = new ChannelPojo();
		channel.setName("channel");
		channel.setType(InvoiceType.CHANNEL);

		channelDao.insert(channel);

		OrderPojo order = new OrderPojo();
		order.setChannelId(channel.getId());
		order.setClient(Party);
		order.setCustomer(customer);
		order.setStatus(OrderStatus.CREATED);
		order.setChannelOrderId("channelorderid");

		orderService.add(order);

		OrderPojo list = orderService.get(order.getId());

		assertEquals(order.getChannelOrderId(), list.getChannelOrderId());
		assertEquals(order.getChannelId(), list.getChannelId());
		assertEquals(order.getClient(), list.getClient());
		assertEquals(order.getStatus(), list.getStatus());
		assertEquals(order.getCustomer(), list.getCustomer());

	}

	@Test
	public void testGetExternalOrders() throws ApiException {
		PartyPojo Party = new PartyPojo();
		Party.setName("assure");
		Party.setType(PartyType.CLIENT);

		partyDao.insert(Party);

		PartyPojo customer = new PartyPojo();
		customer.setName("customer");
		customer.setType(PartyType.CUSTOMER);

		partyDao.insert(customer);

		ChannelPojo channel = new ChannelPojo();
		channel.setName("channel");
		channel.setType(InvoiceType.CHANNEL);

		channelDao.insert(channel);

		OrderPojo order = new OrderPojo();
		order.setChannelId(channel.getId());
		order.setClient(Party);
		order.setCustomer(customer);
		order.setStatus(OrderStatus.CREATED);
		order.setChannelOrderId("channelorderid");

		orderService.add(order);

//		List<OrderPojo> list = orderService.getExternalOrders(1L);
//
//		assertEquals(order.getChannelOrderId(), list.get(0).getChannelOrderId());
//		assertEquals(order.getChannelId(), list.get(0).getChannelId());
//		assertEquals(order.getClient(), list.get(0).getClient());
//		assertEquals(order.getStatus(), list.get(0).getStatus());
//		assertEquals(order.getCustomer(), list.get(0).getCustomer());

	}




	@Test
	public void testSelectAll() throws ApiException {
		PartyPojo Party = new PartyPojo();
		Party.setName("assure");
		Party.setType(PartyType.CLIENT);

		partyDao.insert(Party);

		PartyPojo customer = new PartyPojo();
		customer.setName("customer");
		customer.setType(PartyType.CUSTOMER);

		partyDao.insert(customer);

		ChannelPojo channel = new ChannelPojo();
		channel.setName("channel");
		channel.setType(InvoiceType.CHANNEL);

		channelDao.insert(channel);

		OrderPojo order = new OrderPojo();
		order.setChannelId(channel.getId());
		order.setClient(Party);
		order.setCustomer(customer);
		order.setStatus(OrderStatus.CREATED);
		order.setChannelOrderId("channelorderid");

		orderService.add(order);

		PartyPojo Party1 = new PartyPojo();
		Party1.setName("assure1");
		Party1.setType(PartyType.CLIENT);

		partyDao.insert(Party1);

		PartyPojo customer1 = new PartyPojo();
		customer1.setName("customer1");
		customer1.setType(PartyType.CUSTOMER);

		partyDao.insert(customer1);

		ChannelPojo channel1 = new ChannelPojo();
		channel1.setName("channel1");
		channel1.setType(InvoiceType.CHANNEL);

		channelDao.insert(channel1);

		OrderPojo order1 = new OrderPojo();
		order1.setChannelId(channel1.getId());
		order1.setClient(Party1);
		order1.setCustomer(customer1);
		order1.setStatus(OrderStatus.CREATED);
		order1.setChannelOrderId("channelorderid1");

		orderService.add(order1);

		List<OrderPojo> list = orderService.getAll();
		assertEquals(2, list.size());
		assertEquals(order.getChannelOrderId(), list.get(0).getChannelOrderId());
		assertEquals(order.getChannelId(), list.get(0).getChannelId());
		assertEquals(order.getClient(), list.get(0).getClient());
		assertEquals(order.getStatus(), list.get(0).getStatus());
		assertEquals(order.getCustomer(), list.get(0).getCustomer());

		assertEquals(order1.getChannelOrderId(), list.get(1).getChannelOrderId());
		assertEquals(order1.getChannelId(), list.get(1).getChannelId());
		assertEquals(order1.getClient(), list.get(1).getClient());
		assertEquals(order1.getStatus(), list.get(1).getStatus());
		assertEquals(order1.getCustomer(), list.get(1).getCustomer());

	}


}

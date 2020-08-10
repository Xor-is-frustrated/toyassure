package com.increff.assure.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import com.increff.assure.service.AbstractUnitTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.increff.assure.pojo.ClientPojo;
import com.increff.commons.enums.ClientType;
import com.increff.assure.pojo.OrderPojo;
import com.increff.commons.enums.OrderStatus;

public class OrderDaoTest extends AbstractUnitTest {

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private ClientDao clientDao;



	@Test
	public void testAdd() { 
		ClientPojo client = new ClientPojo();
		client.setName("assure");
		client.setType(ClientType.CLIENT);

		clientDao.insert(client);

		ClientPojo customer = new ClientPojo();
		customer.setName("customer");
		customer.setType(ClientType.CUSTOMER);

		clientDao.insert(customer);

//		ChannelPojo channel = new ChannelPojo();
//		channel.setName("channel");
//		channel.setType(ChannelType.CHANNEL);
//
//		channelDao.insert(channel);

		OrderPojo order = new OrderPojo();
		order.setChannelId(Long.valueOf(1));
		order.setClient(client);
		order.setCustomer(customer);
		order.setStatus(OrderStatus.CREATED);
		order.setChannelOrderId("channelorderid");

		orderDao.insert(order);

	}

	@Test
	public void testSelect() {
		ClientPojo client = new ClientPojo();
		client.setName("assure");
		client.setType(ClientType.CLIENT);

		clientDao.insert(client);

		ClientPojo customer = new ClientPojo();
		customer.setName("customer");
		customer.setType(ClientType.CUSTOMER);

		clientDao.insert(customer);

//		ChannelPojo channel = new ChannelPojo();
//		channel.setName("channel");
//		channel.setType(ChannelType.CHANNEL);
//
//		channelDao.insert(channel);

		OrderPojo order = new OrderPojo();
		order.setChannelId(Long.valueOf(1));
		order.setClient(client);
		order.setCustomer(customer);
		order.setStatus(OrderStatus.CREATED);
		order.setChannelOrderId("channelorderid");

		orderDao.insert(order);

		OrderPojo list = orderDao.select(order.getId());

		assertEquals(order.getChannelOrderId(), list.getChannelOrderId());
		assertEquals(Long.valueOf(1), list.getChannelId());
		assertEquals(order.getClient(), list.getClient());
		assertEquals(order.getStatus(), list.getStatus());
		assertEquals(order.getCustomer(), list.getCustomer());

	}

	@Test
	public void testSelectByChannelOrderId() {
		ClientPojo client = new ClientPojo();
		client.setName("assure");
		client.setType(ClientType.CLIENT);

		clientDao.insert(client);

		ClientPojo customer = new ClientPojo();
		customer.setName("customer");
		customer.setType(ClientType.CUSTOMER);

		clientDao.insert(customer);

//		ChannelPojo channel = new ChannelPojo();
//		channel.setName("channel");
//		channel.setType(ChannelType.CHANNEL);
//
//		channelDao.insert(channel);

		OrderPojo order = new OrderPojo();
		order.setChannelId(Long.valueOf(1));
		order.setClient(client);
		order.setCustomer(customer);
		order.setStatus(OrderStatus.CREATED);
		order.setChannelOrderId("channelorderid");

		orderDao.insert(order);

		OrderPojo list = orderDao.selectByChannelOrderId("channelorderid");

		assertEquals(order.getChannelOrderId(), list.getChannelOrderId());
		assertEquals(Long.valueOf(1), list.getChannelId());
		assertEquals(order.getClient(), list.getClient());
		assertEquals(order.getStatus(), list.getStatus());
		assertEquals(order.getCustomer(), list.getCustomer());

	}

	@Test
	public void testSelectAll() {
		ClientPojo client = new ClientPojo();
		client.setName("assure");
		client.setType(ClientType.CLIENT);

		clientDao.insert(client);

		ClientPojo customer = new ClientPojo();
		customer.setName("customer");
		customer.setType(ClientType.CUSTOMER);

		clientDao.insert(customer);

//		ChannelPojo channel = new ChannelPojo();
//		channel.setName("channel");
//		channel.setType(ChannelType.CHANNEL);
//
//		channelDao.insert(channel);

		OrderPojo order = new OrderPojo();
		order.setChannelId(Long.valueOf(1));
		order.setClient(client);
		order.setCustomer(customer);
		order.setStatus(OrderStatus.CREATED);
		order.setChannelOrderId("channelorderid");

		orderDao.insert(order);

		ClientPojo client1 = new ClientPojo();
		client1.setName("assure1");
		client1.setType(ClientType.CLIENT);

		clientDao.insert(client1);

		ClientPojo customer1 = new ClientPojo();
		customer1.setName("customer1");
		customer1.setType(ClientType.CUSTOMER);

		clientDao.insert(customer1);

//		ChannelPojo channel1 = new ChannelPojo();
//		channel1.setName("channel1");
//		channel1.setType(ChannelType.CHANNEL);
//
//		channelDao.insert(channel1);

		OrderPojo order1 = new OrderPojo();
		order1.setChannelId(Long.valueOf(2));
		order1.setClient(client1);
		order1.setCustomer(customer1);
		order1.setStatus(OrderStatus.CREATED);
		order1.setChannelOrderId("channelorderid1");

		orderDao.insert(order1);

		List<OrderPojo> list = orderDao.selectAll();
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

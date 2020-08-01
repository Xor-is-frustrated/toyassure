package com.increff.assure.dto;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.increff.assure.dao.ChannelDao;
import com.increff.assure.dao.ClientDao;
import com.increff.assure.model.OrderData;
import com.increff.assure.model.OrderForm;
import com.increff.assure.pojo.ChannelPojo;
import com.increff.assure.pojo.ChannelType;
import com.increff.assure.pojo.ClientPojo;
import com.increff.assure.pojo.ClientType;
import com.increff.assure.pojo.OrderPojo;
import com.increff.assure.pojo.OrderStatus;
import com.increff.assure.service.AbstractUnitTest;
import com.increff.assure.service.ApiException;
import com.increff.assure.service.OrderService;

public class OrderDtoTest extends AbstractUnitTest {

	@Autowired
	private ClientDao clientDao;

	@Autowired
	private ChannelDao channelDao;

	@Autowired
	private OrderDto orderDto;

	@Autowired
	private OrderService orderService;

	@Test
	public void testAdd() throws ApiException {
		ClientPojo client = new ClientPojo();
		client.setName("assure");
		client.setType(ClientType.CLIENT);

		clientDao.insert(client);

		ClientPojo customer = new ClientPojo();
		customer.setName("customer");
		customer.setType(ClientType.CUSTOMER);

		clientDao.insert(customer);

		ChannelPojo channel = new ChannelPojo();
		channel.setName("channel");
		channel.setType(ChannelType.CHANNEL);

		channelDao.insert(channel);

		OrderForm form = new OrderForm();
		form.setChannelName("channel");
		form.setChannelOrderId("channelorderid");
		form.setClientName("assure");
		form.setCustomerName("customer");

		OrderData list = orderDto.add(form);

	}

	@Test
	public void testSelect() throws ApiException {
		ClientPojo client = new ClientPojo();
		client.setName("assure");
		client.setType(ClientType.CLIENT);

		clientDao.insert(client);

		ClientPojo customer = new ClientPojo();
		customer.setName("customer");
		customer.setType(ClientType.CUSTOMER);

		clientDao.insert(customer);

		ChannelPojo channel = new ChannelPojo();
		channel.setName("channel");
		channel.setType(ChannelType.CHANNEL);

		channelDao.insert(channel);

		OrderPojo order = new OrderPojo();
		order.setChannel(channel);
		order.setClient(client);
		order.setCustomer(customer);
		order.setStatus(OrderStatus.CREATED);
		order.setChannelOrderId("channelorderid");

		orderService.add(order);

		OrderData list = orderDto.get(order.getId());

		assertEquals(order.getChannelOrderId(), list.getChannelOrderId());
		assertEquals(order.getChannel().getName(), list.getChannelName());
		assertEquals(order.getClient().getName(), list.getClientName());
		assertEquals(order.getCustomer().getName(), list.getCustomerName());

	}

	@Test
	public void testSelectAll() throws ApiException {
		ClientPojo client = new ClientPojo();
		client.setName("assure");
		client.setType(ClientType.CLIENT);

		clientDao.insert(client);

		ClientPojo customer = new ClientPojo();
		customer.setName("customer");
		customer.setType(ClientType.CUSTOMER);

		clientDao.insert(customer);

		ChannelPojo channel = new ChannelPojo();
		channel.setName("channel");
		channel.setType(ChannelType.CHANNEL);

		channelDao.insert(channel);

		OrderPojo order = new OrderPojo();
		order.setChannel(channel);
		order.setClient(client);
		order.setCustomer(customer);
		order.setStatus(OrderStatus.CREATED);
		order.setChannelOrderId("channelorderid");

		orderService.add(order);

		ClientPojo client1 = new ClientPojo();
		client1.setName("assure1");
		client1.setType(ClientType.CLIENT);

		clientDao.insert(client1);

		ClientPojo customer1 = new ClientPojo();
		customer1.setName("customer1");
		customer1.setType(ClientType.CUSTOMER);

		clientDao.insert(customer1);

		ChannelPojo channel1 = new ChannelPojo();
		channel1.setName("channel1");
		channel1.setType(ChannelType.CHANNEL);

		channelDao.insert(channel1);

		OrderPojo order1 = new OrderPojo();
		order1.setChannel(channel1);
		order1.setClient(client1);
		order1.setCustomer(customer1);
		order1.setStatus(OrderStatus.CREATED);
		order1.setChannelOrderId("channelorderid1");

		orderService.add(order1);

		List<OrderData> list = orderDto.getAll();
		assertEquals(2, list.size());
		assertEquals(order.getChannelOrderId(), list.get(0).getChannelOrderId());
		assertEquals(order.getChannel().getName(), list.get(0).getChannelName());
		assertEquals(order.getClient().getName(), list.get(0).getClientName());

		assertEquals(order.getCustomer().getName(), list.get(0).getCustomerName());

		assertEquals(order1.getChannelOrderId(), list.get(1).getChannelOrderId());
		assertEquals(order1.getChannel().getName(), list.get(1).getChannelName());
		assertEquals(order1.getClient().getName(), list.get(1).getClientName());

		assertEquals(order1.getCustomer().getName(), list.get(1).getCustomerName());

	}

}

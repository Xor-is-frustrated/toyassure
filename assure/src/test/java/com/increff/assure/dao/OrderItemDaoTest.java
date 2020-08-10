package com.increff.assure.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


import com.increff.assure.pojo.ClientPojo;
import com.increff.commons.enums.ClientType;
import com.increff.assure.pojo.OrderItemPojo;
import com.increff.assure.pojo.OrderPojo;
import com.increff.commons.enums.OrderStatus;
import com.increff.assure.pojo.ProductPojo;
import com.increff.assure.service.AbstractUnitTest;

public class OrderItemDaoTest extends AbstractUnitTest {

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private ClientDao clientDao;


	@Autowired
	private ProductDao productDao;

	@Autowired
	private OrderItemDao orderItemDao;

	@Test
	public void testAdd() {
		ClientPojo client = new ClientPojo();
		client.setName("assure");
		client.setType(ClientType.CLIENT);

		clientDao.insert(client);

		ProductPojo c = new ProductPojo();
		c.setName("assure");
		c.setBrandId("brand");
		c.setClientSkuId("clientsku");
		c.setDescription("this is description");
		c.setMrp(1.1);
		c.setClient(client);

		productDao.insert(c);

		ClientPojo customer = new ClientPojo();
		customer.setName("customer");
		customer.setType(ClientType.CUSTOMER);

		clientDao.insert(customer);


		OrderPojo order = new OrderPojo();
		order.setChannelId(Long.valueOf(1));
		order.setClient(client);
		order.setCustomer(customer);
		order.setStatus(OrderStatus.CREATED);
		order.setChannelOrderId("channelorderid");

		orderDao.insert(order);

		OrderItemPojo item = new OrderItemPojo();
		item.setOrder(order);
		item.setProduct(c);
		item.setOrderedQuantity(Long.valueOf(100));
		item.setSellingPrice(1.1);
		item.setAllocatedQuantity(Long.valueOf(100));
		item.setFulfilledQuantity(Long.valueOf(100));
		orderItemDao.insert(item);
	}

	@Test
	public void testSelect() {
		ClientPojo client = new ClientPojo();
		client.setName("assure");
		client.setType(ClientType.CLIENT);

		clientDao.insert(client);

		ProductPojo c = new ProductPojo();
		c.setName("assure");
		c.setBrandId("brand");
		c.setClientSkuId("clientsku");
		c.setDescription("this is description");
		c.setMrp(1.1);
		c.setClient(client);

		productDao.insert(c);

		ClientPojo customer = new ClientPojo();
		customer.setName("customer");
		customer.setType(ClientType.CUSTOMER);

		clientDao.insert(customer);



		OrderPojo order = new OrderPojo();
		order.setChannelId(Long.valueOf(1));
		order.setClient(client);
		order.setCustomer(customer);
		order.setStatus(OrderStatus.CREATED);
		order.setChannelOrderId("channelorderid");

		orderDao.insert(order);

		OrderItemPojo item = new OrderItemPojo();
		item.setOrder(order);
		item.setProduct(c);
		item.setOrderedQuantity(Long.valueOf(100));
		item.setSellingPrice(1.1);
		item.setAllocatedQuantity(Long.valueOf(100));
		item.setFulfilledQuantity(Long.valueOf(100));

		orderItemDao.insert(item);

		OrderItemPojo list = orderItemDao.select(item.getId());
		assertEquals(item.getOrder(), list.getOrder());
		assertEquals(item.getProduct(), list.getProduct());
		assertEquals(item.getOrderedQuantity(), list.getOrderedQuantity());
		assertEquals(item.getAllocatedQuantity(), list.getAllocatedQuantity());
		assertEquals(item.getFulfilledQuantity(), list.getFulfilledQuantity());
		assertEquals(item.getSellingPrice(), list.getSellingPrice());

	}

	@Test
	public void testSelectAll() {
		ClientPojo client = new ClientPojo();
		client.setName("assure");
		client.setType(ClientType.CLIENT);

		clientDao.insert(client);

		ProductPojo c = new ProductPojo();
		c.setName("assure");
		c.setBrandId("brand");
		c.setClientSkuId("clientsku");
		c.setDescription("this is description");
		c.setMrp(1.1);
		c.setClient(client);

		productDao.insert(c);

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

		OrderItemPojo item = new OrderItemPojo();
		item.setOrder(order);
		item.setProduct(c);
		item.setOrderedQuantity(Long.valueOf(100));
		item.setSellingPrice(1.1);
		item.setAllocatedQuantity(Long.valueOf(100));
		item.setFulfilledQuantity(Long.valueOf(100));

		orderItemDao.insert(item);

		ClientPojo client1 = new ClientPojo();
		client1.setName("assure1");
		client1.setType(ClientType.CLIENT);

		clientDao.insert(client1);

		ProductPojo c1 = new ProductPojo();
		c1.setName("assure");
		c1.setBrandId("brand");
		c1.setClientSkuId("clientsku");
		c1.setDescription("this is description");
		c1.setMrp(1.1);
		c1.setClient(client1);

		productDao.insert(c1);

//		ChannelPojo channel1 = new ChannelPojo();
//		channel1.setName("channel1");
//		channel1.setType(ChannelType.CHANNEL);
//
//		channelDao.insert(channel1);

		ClientPojo customer1 = new ClientPojo();
		customer1.setName("customer1");
		customer1.setType(ClientType.CUSTOMER);

		clientDao.insert(customer1);

		OrderPojo order1 = new OrderPojo();
		order1.setChannelId(Long.valueOf(2));
		order1.setClient(client1);
		order1.setCustomer(customer1);
		order1.setStatus(OrderStatus.CREATED);
		order1.setChannelOrderId("channelorderid1");

		orderDao.insert(order1);

		OrderItemPojo item1 = new OrderItemPojo();
		item1.setOrder(order1);
		item1.setProduct(c1);
		item1.setOrderedQuantity(Long.valueOf(100));
		item1.setSellingPrice(1.1);
		item1.setAllocatedQuantity(Long.valueOf(100));
		item1.setFulfilledQuantity(Long.valueOf(100));

		orderItemDao.insert(item1);

		List<OrderItemPojo> list = orderItemDao.selectAll();

		assertEquals(2, list.size());
		assertEquals(item.getOrder(), list.get(0).getOrder());
		assertEquals(item.getProduct(), list.get(0).getProduct());
		assertEquals(item.getOrderedQuantity(), list.get(0).getOrderedQuantity());
		assertEquals(item.getAllocatedQuantity(), list.get(0).getAllocatedQuantity());
		assertEquals(item.getFulfilledQuantity(), list.get(0).getFulfilledQuantity());
		assertEquals(item.getSellingPrice(), list.get(0).getSellingPrice());

		assertEquals(item1.getOrder(), list.get(1).getOrder());
		assertEquals(item1.getProduct(), list.get(1).getProduct());
		assertEquals(item1.getOrderedQuantity(), list.get(1).getOrderedQuantity());
		assertEquals(item1.getAllocatedQuantity(), list.get(1).getAllocatedQuantity());
		assertEquals(item1.getFulfilledQuantity(), list.get(1).getFulfilledQuantity());
		assertEquals(item1.getSellingPrice(), list.get(1).getSellingPrice());

	}
	
	@Test
	public void testSelectByOrder() {
		ClientPojo client = new ClientPojo();
		client.setName("assure");
		client.setType(ClientType.CLIENT);

		clientDao.insert(client);

		ProductPojo c = new ProductPojo();
		c.setName("assure");
		c.setBrandId("brand");
		c.setClientSkuId("clientsku");
		c.setDescription("this is description");
		c.setMrp(1.1);
		c.setClient(client);

		productDao.insert(c);

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

		OrderItemPojo item = new OrderItemPojo();
		item.setOrder(order);
		item.setProduct(c);
		item.setOrderedQuantity(Long.valueOf(100));
		item.setSellingPrice(1.1);
		item.setAllocatedQuantity(Long.valueOf(100));
		item.setFulfilledQuantity(Long.valueOf(100));

		orderItemDao.insert(item);

		ClientPojo client1 = new ClientPojo();
		client1.setName("assure1");
		client1.setType(ClientType.CLIENT);

		clientDao.insert(client1);

		ProductPojo c1 = new ProductPojo();
		c1.setName("assure");
		c1.setBrandId("brand");
		c1.setClientSkuId("clientsku");
		c1.setDescription("this is description");
		c1.setMrp(1.1);
		c1.setClient(client1);

		productDao.insert(c1);

//		ChannelPojo channel1 = new ChannelPojo();
//		channel1.setName("channel1");
//		channel1.setType(ChannelType.CHANNEL);
//
//		channelDao.insert(channel1);

		ClientPojo customer1 = new ClientPojo();
		customer1.setName("customer1");
		customer1.setType(ClientType.CUSTOMER);

		clientDao.insert(customer1);

		OrderPojo order1 = new OrderPojo();
		order1.setChannelId(Long.valueOf(2));
		order1.setClient(client1);
		order1.setCustomer(customer1);
		order1.setStatus(OrderStatus.CREATED);
		order1.setChannelOrderId("channelorderid1");

		orderDao.insert(order1);

		OrderItemPojo item1 = new OrderItemPojo();
		item1.setOrder(order);
		item1.setProduct(c1);
		item1.setOrderedQuantity(Long.valueOf(100));
		item1.setSellingPrice(1.1);
		item1.setAllocatedQuantity(Long.valueOf(100));
		item1.setFulfilledQuantity(Long.valueOf(100));

		orderItemDao.insert(item1);

		List<OrderItemPojo> list = orderItemDao.selectByOrder(order);

		assertEquals(2, list.size());
		assertEquals(item.getOrder(), list.get(0).getOrder());
		assertEquals(item.getProduct(), list.get(0).getProduct());
		assertEquals(item.getOrderedQuantity(), list.get(0).getOrderedQuantity());
		assertEquals(item.getAllocatedQuantity(), list.get(0).getAllocatedQuantity());
		assertEquals(item.getFulfilledQuantity(), list.get(0).getFulfilledQuantity());
		assertEquals(item.getSellingPrice(), list.get(0).getSellingPrice());

		assertEquals(item1.getOrder(), list.get(1).getOrder());
		assertEquals(item1.getProduct(), list.get(1).getProduct());
		assertEquals(item1.getOrderedQuantity(), list.get(1).getOrderedQuantity());
		assertEquals(item1.getAllocatedQuantity(), list.get(1).getAllocatedQuantity());
		assertEquals(item1.getFulfilledQuantity(), list.get(1).getFulfilledQuantity());
		assertEquals(item1.getSellingPrice(), list.get(1).getSellingPrice());

	}

	@Test
	public void testSelectByOrderAndProduct() {
		ClientPojo client = new ClientPojo();
		client.setName("assure");
		client.setType(ClientType.CLIENT);

		clientDao.insert(client);

		ProductPojo c = new ProductPojo();
		c.setName("assure");
		c.setBrandId("brand");
		c.setClientSkuId("clientsku");
		c.setDescription("this is description");
		c.setMrp(1.1);
		c.setClient(client);

		productDao.insert(c);

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

		OrderItemPojo item = new OrderItemPojo();
		item.setOrder(order);
		item.setProduct(c);
		item.setOrderedQuantity(Long.valueOf(100));
		item.setSellingPrice(1.1);
		item.setAllocatedQuantity(Long.valueOf(100));
		item.setFulfilledQuantity(Long.valueOf(100));

		orderItemDao.insert(item);

		ClientPojo client1 = new ClientPojo();
		client1.setName("assure1");
		client1.setType(ClientType.CLIENT);

		clientDao.insert(client1);

		ProductPojo c1 = new ProductPojo();
		c1.setName("assure");
		c1.setBrandId("brand");
		c1.setClientSkuId("clientsku");
		c1.setDescription("this is description");
		c1.setMrp(1.1);
		c1.setClient(client1);

		productDao.insert(c1);

//		ChannelPojo channel1 = new ChannelPojo();
//		channel1.setName("channel1");
//		channel1.setType(ChannelType.CHANNEL);
//
//		channelDao.insert(channel1);

		ClientPojo customer1 = new ClientPojo();
		customer1.setName("customer1");
		customer1.setType(ClientType.CUSTOMER);

		clientDao.insert(customer1);

		OrderPojo order1 = new OrderPojo();
		order1.setChannelId(Long.valueOf(2));
		order1.setClient(client1);
		order1.setCustomer(customer1);
		order1.setStatus(OrderStatus.CREATED);
		order1.setChannelOrderId("channelorderid1");

		orderDao.insert(order1);

		OrderItemPojo item1 = new OrderItemPojo();
		item1.setOrder(order1);
		item1.setProduct(c1);
		item1.setOrderedQuantity(Long.valueOf(100));
		item1.setSellingPrice(1.1);
		item1.setAllocatedQuantity(Long.valueOf(100));
		item1.setFulfilledQuantity(Long.valueOf(100));

		orderItemDao.insert(item1);

		OrderItemPojo list = orderItemDao.selectByOrderAndProduct(order, c);

		assertEquals(item.getOrder(), list.getOrder());
		assertEquals(item.getProduct(), list.getProduct());
		assertEquals(item.getOrderedQuantity(), list.getOrderedQuantity());
		assertEquals(item.getAllocatedQuantity(), list.getAllocatedQuantity());
		assertEquals(item.getFulfilledQuantity(), list.getFulfilledQuantity());
		assertEquals(item.getSellingPrice(), list.getSellingPrice());

	}

	@Test
	public void testDelete() {
		ClientPojo client = new ClientPojo();
		client.setName("assure");
		client.setType(ClientType.CLIENT);

		clientDao.insert(client);

		ProductPojo c = new ProductPojo();
		c.setName("assure");
		c.setBrandId("brand");
		c.setClientSkuId("clientsku");
		c.setDescription("this is description");
		c.setMrp(1.1);
		c.setClient(client);

		productDao.insert(c);

		ClientPojo customer = new ClientPojo();
		customer.setName("customer");
		customer.setType(ClientType.CUSTOMER);

		clientDao.insert(customer);



		OrderPojo order = new OrderPojo();
		order.setChannelId(Long.valueOf(1));
		order.setClient(client);
		order.setCustomer(customer);
		order.setStatus(OrderStatus.CREATED);
		order.setChannelOrderId("channelorderid");

		orderDao.insert(order);

		OrderItemPojo item = new OrderItemPojo();
		item.setOrder(order);
		item.setProduct(c);
		item.setOrderedQuantity(Long.valueOf(100));
		item.setSellingPrice(1.1);
		item.setAllocatedQuantity(Long.valueOf(100));
		item.setFulfilledQuantity(Long.valueOf(100));
		
		orderItemDao.insert(item);
		Long id= item.getId();
		orderItemDao.delete(item.getId());

		OrderItemPojo list = orderItemDao.select(id);
		assertEquals(null, list);
	}

}

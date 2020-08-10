package com.increff.assure.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.increff.assure.dao.ClientDao;
import com.increff.assure.dao.OrderDao;
import com.increff.assure.dao.ProductDao;

public class OrderItemServiceTest extends AbstractUnitTest {

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private ClientDao clientDao;


	@Autowired
	private ProductDao productDao;

	@Autowired
	private OrderItemService orderItemService;

	@Test
	public void test(){

	}

//	@Test
//	public void testAdd() throws ApiException {
//		ClientPojo client = new ClientPojo();
//		client.setName("assure");
//		client.setType(ClientType.CLIENT);
//
//		clientDao.insert(client);
//
//		ProductPojo c = new ProductPojo();
//		c.setName("assure");
//		c.setBrandId("brand");
//		c.setClientSkuId("clientsku");
//		c.setDescription("this is description");
//		c.setMrp(1.1);
//		c.setClient(client);
//
//		productDao.insert(c);
//
//		ClientPojo customer = new ClientPojo();
//		customer.setName("customer");
//		customer.setType(ClientType.CUSTOMER);
//
//		clientDao.insert(customer);
//
//		ChannelPojo channel = new ChannelPojo();
//		channel.setName("channel");
//		channel.setType(ChannelType.CHANNEL);
//
//		channelDao.insert(channel);
//
//		OrderPojo order = new OrderPojo();
//		order.setChannelId(channel);
//		order.setClient(client);
//		order.setCustomer(customer);
//		order.setStatus(OrderStatus.CREATED);
//		order.setChannelOrderId("channelorderid");
//
//		orderDao.insert(order);
//
//		OrderItemPojo item = new OrderItemPojo();
//		item.setOrder(order);
//		item.setProduct(c);
//		item.setOrderedQuantity(Long.valueOf(100));
//		item.setSellingPrice(1.1);
//		item.setAllocatedQuantity(Long.valueOf(100));
//		item.setFulfilledQuantity(Long.valueOf(100));
//		orderItemService.add(item);
//	}
//
//	@Test
//	public void testSelect() throws ApiException {
//		ClientPojo client = new ClientPojo();
//		client.setName("assure");
//		client.setType(ClientType.CLIENT);
//
//		clientDao.insert(client);
//
//		ProductPojo c = new ProductPojo();
//		c.setName("assure");
//		c.setBrandId("brand");
//		c.setClientSkuId("clientsku");
//		c.setDescription("this is description");
//		c.setMrp(1.1);
//		c.setClient(client);
//
//		productDao.insert(c);
//
//		ClientPojo customer = new ClientPojo();
//		customer.setName("customer");
//		customer.setType(ClientType.CUSTOMER);
//
//		clientDao.insert(customer);
//
//		ChannelPojo channel = new ChannelPojo();
//		channel.setName("channel");
//		channel.setType(ChannelType.CHANNEL);
//
//		channelDao.insert(channel);
//
//		OrderPojo order = new OrderPojo();
//		order.setChannelId(channel);
//		order.setClient(client);
//		order.setCustomer(customer);
//		order.setStatus(OrderStatus.CREATED);
//		order.setChannelOrderId("channelorderid");
//
//		orderDao.insert(order);
//
//		OrderItemPojo item = new OrderItemPojo();
//		item.setOrder(order);
//		item.setProduct(c);
//		item.setOrderedQuantity(Long.valueOf(100));
//		item.setSellingPrice(1.1);
//		item.setAllocatedQuantity(Long.valueOf(100));
//		item.setFulfilledQuantity(Long.valueOf(100));
//
//		orderItemService.add(item);
//
//		OrderItemPojo list = orderItemService.get(item.getId());
//		assertEquals(item.getOrder(), list.getOrder());
//		assertEquals(item.getProduct(), list.getProduct());
//		assertEquals(item.getOrderedQuantity(), list.getOrderedQuantity());
//		assertEquals(item.getAllocatedQuantity(), list.getAllocatedQuantity());
//		assertEquals(item.getFulfilledQuantity(), list.getFulfilledQuantity());
//		assertEquals(item.getSellingPrice(), list.getSellingPrice());
//
//	}
//
//	@Test
//	public void testUpdate() throws ApiException {
//		ClientPojo client = new ClientPojo();
//		client.setName("assure");
//		client.setType(ClientType.CLIENT);
//
//		clientDao.insert(client);
//
//		ProductPojo c = new ProductPojo();
//		c.setName("assure");
//		c.setBrandId("brand");
//		c.setClientSkuId("clientsku");
//		c.setDescription("this is description");
//		c.setMrp(1.1);
//		c.setClient(client);
//
//		productDao.insert(c);
//
//		ClientPojo customer = new ClientPojo();
//		customer.setName("customer");
//		customer.setType(ClientType.CUSTOMER);
//
//		clientDao.insert(customer);
//
//		ChannelPojo channel = new ChannelPojo();
//		channel.setName("channel");
//		channel.setType(ChannelType.CHANNEL);
//
//		channelDao.insert(channel);
//
//		OrderPojo order = new OrderPojo();
//		order.setChannelId(channel);
//		order.setClient(client);
//		order.setCustomer(customer);
//		order.setStatus(OrderStatus.CREATED);
//		order.setChannelOrderId("channelorderid");
//
//		orderDao.insert(order);
//
//		OrderItemPojo item = new OrderItemPojo();
//		item.setOrder(order);
//		item.setProduct(c);
//		item.setOrderedQuantity(Long.valueOf(100));
//		item.setSellingPrice(1.1);
//		item.setAllocatedQuantity(Long.valueOf(100));
//		item.setFulfilledQuantity(Long.valueOf(100));
//
//		orderItemService.add(item);
//
//		OrderItemPojo list = orderItemService.update(item.getId(), Long.valueOf(11), Long.valueOf(11), Long.valueOf(11),
//				11.1);
//		assertEquals(item.getOrder(), list.getOrder());
//		assertEquals(item.getProduct(), list.getProduct());
//		assertEquals(Long.valueOf(11), list.getOrderedQuantity());
//		assertEquals(Long.valueOf(11), list.getAllocatedQuantity());
//		assertEquals(Long.valueOf(11), list.getFulfilledQuantity());
//
//	}
//
//	@Test
//	public void testSelectAll() throws ApiException {
//		ClientPojo client = new ClientPojo();
//		client.setName("assure");
//		client.setType(ClientType.CLIENT);
//
//		clientDao.insert(client);
//
//		ProductPojo c = new ProductPojo();
//		c.setName("assure");
//		c.setBrandId("brand");
//		c.setClientSkuId("clientsku");
//		c.setDescription("this is description");
//		c.setMrp(1.1);
//		c.setClient(client);
//
//		productDao.insert(c);
//
//		ClientPojo customer = new ClientPojo();
//		customer.setName("customer");
//		customer.setType(ClientType.CUSTOMER);
//
//		clientDao.insert(customer);
//
//		ChannelPojo channel = new ChannelPojo();
//		channel.setName("channel");
//		channel.setType(ChannelType.CHANNEL);
//
//		channelDao.insert(channel);
//
//		OrderPojo order = new OrderPojo();
//		order.setChannelId(channel);
//		order.setClient(client);
//		order.setCustomer(customer);
//		order.setStatus(OrderStatus.CREATED);
//		order.setChannelOrderId("channelorderid");
//
//		orderDao.insert(order);
//
//		OrderItemPojo item = new OrderItemPojo();
//		item.setOrder(order);
//		item.setProduct(c);
//		item.setOrderedQuantity(Long.valueOf(100));
//		item.setSellingPrice(1.1);
//		item.setAllocatedQuantity(Long.valueOf(100));
//		item.setFulfilledQuantity(Long.valueOf(100));
//
//		orderItemService.add(item);
//
//		ClientPojo client1 = new ClientPojo();
//		client1.setName("assure1");
//		client1.setType(ClientType.CLIENT);
//
//		clientDao.insert(client1);
//
//		ProductPojo c1 = new ProductPojo();
//		c1.setName("assure");
//		c1.setBrandId("brand");
//		c1.setClientSkuId("clientsku");
//		c1.setDescription("this is description");
//		c1.setMrp(1.1);
//		c1.setClient(client1);
//
//		productDao.insert(c1);
//
//		ChannelPojo channel1 = new ChannelPojo();
//		channel1.setName("channel1");
//		channel1.setType(ChannelType.CHANNEL);
//
//		channelDao.insert(channel1);
//
//		ClientPojo customer1 = new ClientPojo();
//		customer1.setName("customer1");
//		customer1.setType(ClientType.CUSTOMER);
//
//		clientDao.insert(customer1);
//
//		OrderPojo order1 = new OrderPojo();
//		order1.setChannelId(channel1);
//		order1.setClient(client1);
//		order1.setCustomer(customer1);
//		order1.setStatus(OrderStatus.CREATED);
//		order1.setChannelOrderId("channelorderid1");
//
//		orderDao.insert(order1);
//
//		OrderItemPojo item1 = new OrderItemPojo();
//		item1.setOrder(order1);
//		item1.setProduct(c1);
//		item1.setOrderedQuantity(Long.valueOf(100));
//		item1.setSellingPrice(1.1);
//		item1.setAllocatedQuantity(Long.valueOf(100));
//		item1.setFulfilledQuantity(Long.valueOf(100));
//
//		orderItemService.add(item1);
//
//		List<OrderItemPojo> list = orderItemService.getAll();
//
//		assertEquals(2, list.size());
//		assertEquals(item.getOrder(), list.get(0).getOrder());
//		assertEquals(item.getProduct(), list.get(0).getProduct());
//		assertEquals(item.getOrderedQuantity(), list.get(0).getOrderedQuantity());
//		assertEquals(item.getAllocatedQuantity(), list.get(0).getAllocatedQuantity());
//		assertEquals(item.getFulfilledQuantity(), list.get(0).getFulfilledQuantity());
//		assertEquals(item.getSellingPrice(), list.get(0).getSellingPrice());
//
//		assertEquals(item1.getOrder(), list.get(1).getOrder());
//		assertEquals(item1.getProduct(), list.get(1).getProduct());
//		assertEquals(item1.getOrderedQuantity(), list.get(1).getOrderedQuantity());
//		assertEquals(item1.getAllocatedQuantity(), list.get(1).getAllocatedQuantity());
//		assertEquals(item1.getFulfilledQuantity(), list.get(1).getFulfilledQuantity());
//		assertEquals(item1.getSellingPrice(), list.get(1).getSellingPrice());
//
//	}
//
//	@Test(expected = ApiException.class)
//	public void testDelete() throws ApiException {
//		ClientPojo client = new ClientPojo();
//		client.setName("assure");
//		client.setType(ClientType.CLIENT);
//
//		clientDao.insert(client);
//
//		ProductPojo c = new ProductPojo();
//		c.setName("assure");
//		c.setBrandId("brand");
//		c.setClientSkuId("clientsku");
//		c.setDescription("this is description");
//		c.setMrp(1.1);
//		c.setClient(client);
//
//		productDao.insert(c);
//
//		ClientPojo customer = new ClientPojo();
//		customer.setName("customer");
//		customer.setType(ClientType.CUSTOMER);
//
//		clientDao.insert(customer);
//
//		ChannelPojo channel = new ChannelPojo();
//		channel.setName("channel");
//		channel.setType(ChannelType.CHANNEL);
//
//		channelDao.insert(channel);
//
//		OrderPojo order = new OrderPojo();
//		order.setChannelId(channel);
//		order.setClient(client);
//		order.setCustomer(customer);
//		order.setStatus(OrderStatus.CREATED);
//		order.setChannelOrderId("channelorderid");
//
//		orderDao.insert(order);
//
//		OrderItemPojo item = new OrderItemPojo();
//		item.setOrder(order);
//		item.setProduct(c);
//		item.setOrderedQuantity(Long.valueOf(100));
//		item.setSellingPrice(1.1);
//		item.setAllocatedQuantity(Long.valueOf(100));
//		item.setFulfilledQuantity(Long.valueOf(100));
//
//		orderItemService.add(item);
//		orderItemService.delete(item.getId());
//
//		OrderItemPojo list = orderItemService.get(item.getId());
//
//
//	}

}

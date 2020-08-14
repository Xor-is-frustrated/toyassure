package com.increff.assure.service;

import static org.junit.Assert.assertEquals;

import com.increff.assure.dao.ChannelDao;
import com.increff.assure.pojo.*;
import com.increff.commons.enums.InvoiceType;
import com.increff.commons.enums.OrderStatus;
import com.increff.commons.enums.PartyType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.increff.assure.dao.PartyDao;
import com.increff.assure.dao.OrderDao;
import com.increff.assure.dao.ProductDao;

import java.util.List;

public class OrderItemServiceTest extends AbstractUnitTest {

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private PartyDao partyDao;

	@Autowired
	private ChannelDao channelDao;

	@Autowired
	private ProductDao productDao;

	@Autowired
	private OrderItemService orderItemService;



	@Test
	public void testAdd() throws ApiException {
		PartyPojo Party = new PartyPojo();
		Party.setName("assure");
		Party.setType(PartyType.CLIENT);

		partyDao.insert(Party);

		ProductPojo c = new ProductPojo();
		c.setName("assure");
		c.setBrandId("brand");
		c.setClientSkuId("Partysku");
		c.setDescription("this is description");
		c.setMrp(1.1);
		c.setParty(Party);

		productDao.insert(c);

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

		orderDao.insert(order);

		OrderItemPojo item = new OrderItemPojo();
		item.setOrder(order);
		item.setProduct(c);
		item.setOrderedQuantity(Long.valueOf(100));
		item.setSellingPrice(1.1);
		item.setAllocatedQuantity(Long.valueOf(100));
		item.setFulfilledQuantity(Long.valueOf(100));
		orderItemService.add(item);
	}

	@Test
	public void testSelect() throws ApiException {
		PartyPojo Party = new PartyPojo();
		Party.setName("assure");
		Party.setType(PartyType.CLIENT);

		partyDao.insert(Party);

		ProductPojo c = new ProductPojo();
		c.setName("assure");
		c.setBrandId("brand");
		c.setClientSkuId("Partysku");
		c.setDescription("this is description");
		c.setMrp(1.1);
		c.setParty(Party);

		productDao.insert(c);

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

		orderDao.insert(order);

		OrderItemPojo item = new OrderItemPojo();
		item.setOrder(order);
		item.setProduct(c);
		item.setOrderedQuantity(Long.valueOf(100));
		item.setSellingPrice(1.1);
		item.setAllocatedQuantity(Long.valueOf(100));
		item.setFulfilledQuantity(Long.valueOf(100));
		orderItemService.add(item);

		OrderItemPojo list = orderItemService.get(item.getId());
		assertEquals(item.getOrder(), list.getOrder());
		assertEquals(item.getProduct(), list.getProduct());
		assertEquals(item.getOrderedQuantity(), list.getOrderedQuantity());
		assertEquals(item.getAllocatedQuantity(), list.getAllocatedQuantity());
		assertEquals(item.getFulfilledQuantity(), list.getFulfilledQuantity());
		assertEquals(item.getSellingPrice(), list.getSellingPrice());

	}

	@Test
	public void testUpdate() throws ApiException {
		PartyPojo Party = new PartyPojo();
		Party.setName("assure");
		Party.setType(PartyType.CLIENT);

		partyDao.insert(Party);

		ProductPojo c = new ProductPojo();
		c.setName("assure");
		c.setBrandId("brand");
		c.setClientSkuId("Partysku");
		c.setDescription("this is description");
		c.setMrp(1.1);
		c.setParty(Party);

		productDao.insert(c);

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

		orderDao.insert(order);

		OrderItemPojo item = new OrderItemPojo();
		item.setOrder(order);
		item.setProduct(c);
		item.setOrderedQuantity(Long.valueOf(100));
		item.setSellingPrice(1.1);
		item.setAllocatedQuantity(Long.valueOf(100));
		item.setFulfilledQuantity(Long.valueOf(100));
		orderItemService.add(item);

		OrderItemPojo list = orderItemService.update(item.getId(), Long.valueOf(11), Long.valueOf(11), Long.valueOf(11),
				11.1);
		assertEquals(item.getOrder(), list.getOrder());
		assertEquals(item.getProduct(), list.getProduct());
		assertEquals(Long.valueOf(11), list.getOrderedQuantity());
		assertEquals(Long.valueOf(11), list.getAllocatedQuantity());
		assertEquals(Long.valueOf(11), list.getFulfilledQuantity());

	}

	@Test
	public void testSelectAll() throws ApiException {
		PartyPojo Party = new PartyPojo();
		Party.setName("assure");
		Party.setType(PartyType.CLIENT);

		partyDao.insert(Party);

		ProductPojo c = new ProductPojo();
		c.setName("assure");
		c.setBrandId("brand");
		c.setClientSkuId("Partysku");
		c.setDescription("this is description");
		c.setMrp(1.1);
		c.setParty(Party);

		productDao.insert(c);

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

		orderDao.insert(order);

		OrderItemPojo item = new OrderItemPojo();
		item.setOrder(order);
		item.setProduct(c);
		item.setOrderedQuantity(Long.valueOf(100));
		item.setSellingPrice(1.1);
		item.setAllocatedQuantity(Long.valueOf(100));
		item.setFulfilledQuantity(Long.valueOf(100));
		orderItemService.add(item);

		PartyPojo Party1 = new PartyPojo();
		Party1.setName("assure1");
		Party1.setType(PartyType.CLIENT);

		partyDao.insert(Party1);

		ProductPojo c1 = new ProductPojo();
		c1.setName("assure");
		c1.setBrandId("brand");
		c1.setClientSkuId("Partysku");
		c1.setDescription("this is description");
		c1.setMrp(1.1);
		c1.setParty(Party1);

		productDao.insert(c1);

		ChannelPojo channel1 = new ChannelPojo();
		channel1.setName("channel1");
		channel1.setType(InvoiceType.CHANNEL);

		channelDao.insert(channel1);

		PartyPojo customer1 = new PartyPojo();
		customer1.setName("customer1");
		customer1.setType(PartyType.CUSTOMER);

		partyDao.insert(customer1);

		OrderPojo order1 = new OrderPojo();
		order1.setChannelId(channel1.getId());
		order1.setClient(Party1);
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

		orderItemService.add(item1);

		List<OrderItemPojo> list = orderItemService.getAll();

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

	@Test(expected = ApiException.class)
	public void testDelete() throws ApiException {
		PartyPojo Party = new PartyPojo();
		Party.setName("assure");
		Party.setType(PartyType.CLIENT);

		partyDao.insert(Party);

		ProductPojo c = new ProductPojo();
		c.setName("assure");
		c.setBrandId("brand");
		c.setClientSkuId("Partysku");
		c.setDescription("this is description");
		c.setMrp(1.1);
		c.setParty(Party);

		productDao.insert(c);

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

		orderDao.insert(order);

		OrderItemPojo item = new OrderItemPojo();
		item.setOrder(order);
		item.setProduct(c);
		item.setOrderedQuantity(Long.valueOf(100));
		item.setSellingPrice(1.1);
		item.setAllocatedQuantity(Long.valueOf(100));
		item.setFulfilledQuantity(Long.valueOf(100));
		orderItemService.add(item);
		orderItemService.delete(item.getId());

		OrderItemPojo list = orderItemService.get(item.getId());


	}

}

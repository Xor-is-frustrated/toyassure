package com.increff.assure.dao;

import com.increff.assure.pojo.OrderItemPojo;
import com.increff.assure.pojo.OrderPojo;
import com.increff.assure.pojo.PartyPojo;
import com.increff.assure.pojo.ProductPojo;
import com.increff.assure.service.AbstractUnitTest;
import com.increff.commons.enums.OrderStatus;
import com.increff.commons.enums.PartyType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class OrderItemDaoTest extends AbstractUnitTest {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private PartyDao partyDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private OrderItemDao orderItemDao;

    @Test
    public void testAdd() {
        PartyPojo client = new PartyPojo();
        client.setName("assure");
        client.setType(PartyType.CLIENT);
        partyDao.insert(client);
        ProductPojo c = new ProductPojo();
        c.setName("assure");
        c.setBrandId("brand");
        c.setClientSkuId("clientsku");
        c.setDescription("this is description");
        c.setMrp(1.1);
        c.setParty(client);
        productDao.insert(c);
        PartyPojo customer = new PartyPojo();
        customer.setName("customer");
        customer.setType(PartyType.CUSTOMER);
        partyDao.insert(customer);
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
        PartyPojo client = new PartyPojo();
        client.setName("assure");
        client.setType(PartyType.CLIENT);
        partyDao.insert(client);
        ProductPojo c = new ProductPojo();
        c.setName("assure");
        c.setBrandId("brand");
        c.setClientSkuId("clientsku");
        c.setDescription("this is description");
        c.setMrp(1.1);
        c.setParty(client);
        productDao.insert(c);
        PartyPojo customer = new PartyPojo();
        customer.setName("customer");
        customer.setType(PartyType.CUSTOMER);
        partyDao.insert(customer);
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
        PartyPojo client = new PartyPojo();
        client.setName("assure");
        client.setType(PartyType.CLIENT);
        partyDao.insert(client);
        ProductPojo c = new ProductPojo();
        c.setName("assure");
        c.setBrandId("brand");
        c.setClientSkuId("clientsku");
        c.setDescription("this is description");
        c.setMrp(1.1);
        c.setParty(client);
        productDao.insert(c);
        PartyPojo customer = new PartyPojo();
        customer.setName("customer");
        customer.setType(PartyType.CUSTOMER);
        partyDao.insert(customer);
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
        PartyPojo client1 = new PartyPojo();
        client1.setName("assure1");
        client1.setType(PartyType.CLIENT);
        partyDao.insert(client1);
        ProductPojo c1 = new ProductPojo();
        c1.setName("assure");
        c1.setBrandId("brand");
        c1.setClientSkuId("clientsku");
        c1.setDescription("this is description");
        c1.setMrp(1.1);
        c1.setParty(client1);
        productDao.insert(c1);
        PartyPojo customer1 = new PartyPojo();
        customer1.setName("customer1");
        customer1.setType(PartyType.CUSTOMER);
        partyDao.insert(customer1);
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
        PartyPojo client = new PartyPojo();
        client.setName("assure");
        client.setType(PartyType.CLIENT);
        partyDao.insert(client);
        ProductPojo c = new ProductPojo();
        c.setName("assure");
        c.setBrandId("brand");
        c.setClientSkuId("clientsku");
        c.setDescription("this is description");
        c.setMrp(1.1);
        c.setParty(client);
        productDao.insert(c);
        PartyPojo customer = new PartyPojo();
        customer.setName("customer");
        customer.setType(PartyType.CUSTOMER);
        partyDao.insert(customer);
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
        PartyPojo client1 = new PartyPojo();
        client1.setName("assure1");
        client1.setType(PartyType.CLIENT);
        partyDao.insert(client1);
        ProductPojo c1 = new ProductPojo();
        c1.setName("assure");
        c1.setBrandId("brand");
        c1.setClientSkuId("clientsku");
        c1.setDescription("this is description");
        c1.setMrp(1.1);
        c1.setParty(client1);
        productDao.insert(c1);
        PartyPojo customer1 = new PartyPojo();
        customer1.setName("customer1");
        customer1.setType(PartyType.CUSTOMER);
        partyDao.insert(customer1);
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
        List<OrderItemPojo> list = orderItemDao.selectByOrderId(order.getId());
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
        PartyPojo client = new PartyPojo();
        client.setName("assure");
        client.setType(PartyType.CLIENT);
        partyDao.insert(client);
        ProductPojo c = new ProductPojo();
        c.setName("assure");
        c.setBrandId("brand");
        c.setClientSkuId("clientsku");
        c.setDescription("this is description");
        c.setMrp(1.1);
        c.setParty(client);
        productDao.insert(c);
        PartyPojo customer = new PartyPojo();
        customer.setName("customer");
        customer.setType(PartyType.CUSTOMER);
        partyDao.insert(customer);
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
        PartyPojo client1 = new PartyPojo();
        client1.setName("assure1");
        client1.setType(PartyType.CLIENT);
        partyDao.insert(client1);
        ProductPojo c1 = new ProductPojo();
        c1.setName("assure");
        c1.setBrandId("brand");
        c1.setClientSkuId("clientsku");
        c1.setDescription("this is description");
        c1.setMrp(1.1);
        c1.setParty(client1);
        productDao.insert(c1);
//		ChannelPojo channel1 = new ChannelPojo();
//		channel1.setName("channel1");
//		channel1.setType(ChannelType.CHANNEL);
//
//		channelDao.insert(channel1);
        PartyPojo customer1 = new PartyPojo();
        customer1.setName("customer1");
        customer1.setType(PartyType.CUSTOMER);
        partyDao.insert(customer1);
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
        OrderItemPojo list = orderItemDao.selectByOrderIdAndGlobalSkuId(order.getId(), c.getGlobalSkuId());
        assertEquals(item.getOrder(), list.getOrder());
        assertEquals(item.getProduct(), list.getProduct());
        assertEquals(item.getOrderedQuantity(), list.getOrderedQuantity());
        assertEquals(item.getAllocatedQuantity(), list.getAllocatedQuantity());
        assertEquals(item.getFulfilledQuantity(), list.getFulfilledQuantity());
        assertEquals(item.getSellingPrice(), list.getSellingPrice());

    }

    @Test
    public void testDelete() {
        PartyPojo client = new PartyPojo();
        client.setName("assure");
        client.setType(PartyType.CLIENT);
        partyDao.insert(client);
        ProductPojo c = new ProductPojo();
        c.setName("assure");
        c.setBrandId("brand");
        c.setClientSkuId("clientsku");
        c.setDescription("this is description");
        c.setMrp(1.1);
        c.setParty(client);
        productDao.insert(c);
        PartyPojo customer = new PartyPojo();
        customer.setName("customer");
        customer.setType(PartyType.CUSTOMER);
        partyDao.insert(customer);
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
        Long id = item.getId();
        orderItemDao.delete(item.getId());
        OrderItemPojo list = orderItemDao.select(id);
        assertEquals(null, list);
    }

}

package com.increff.assure.dao;

import com.increff.assure.pojo.ChannelPojo;
import com.increff.assure.pojo.OrderPojo;
import com.increff.assure.pojo.PartyPojo;
import com.increff.assure.service.AbstractUnitTest;
import com.increff.commons.enums.InvoiceType;
import com.increff.commons.enums.OrderStatus;
import com.increff.commons.enums.PartyType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class OrderDaoTest extends AbstractUnitTest {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private PartyDao partyDao;

    @Autowired
    private ChannelDao channelDao;

    @Test
    public void testAdd() {
        PartyPojo client = new PartyPojo();
        client.setName("assure");
        client.setType(PartyType.CLIENT);
        partyDao.insert(client);
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

    }

    @Test
    public void testSelect() {
        PartyPojo client = new PartyPojo();
        client.setName("assure");
        client.setType(PartyType.CLIENT);
        partyDao.insert(client);
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
        OrderPojo list = orderDao.select(order.getId());
        assertEquals(order.getChannelOrderId(), list.getChannelOrderId());
        assertEquals(Long.valueOf(1), list.getChannelId());
        assertEquals(order.getClient(), list.getClient());
        assertEquals(order.getStatus(), list.getStatus());
        assertEquals(order.getCustomer(), list.getCustomer());

    }

    @Test
    public void testSelectExternalOrders() {
        PartyPojo client = new PartyPojo();
        client.setName("assure");
        client.setType(PartyType.CLIENT);
        partyDao.insert(client);
        PartyPojo customer = new PartyPojo();
        customer.setName("customer");
        customer.setType(PartyType.CUSTOMER);
        partyDao.insert(customer);
        ChannelPojo channel = new ChannelPojo();
        channel.setName("name");
        channel.setType(InvoiceType.CHANNEL);
        channelDao.insert(channel);
        ChannelPojo channel1 = new ChannelPojo();
        channel1.setName("name1");
        channel1.setType(InvoiceType.CHANNEL);
        channelDao.insert(channel1);
        OrderPojo order = new OrderPojo();
        order.setChannelId(Long.valueOf(2));
        order.setClient(client);
        order.setCustomer(customer);
        order.setStatus(OrderStatus.CREATED);
        order.setChannelOrderId("channelorderid");
        orderDao.insert(order);
        List<OrderPojo> list = orderDao.selectExternalOrders(1L);
        assertEquals(order.getChannelOrderId(), list.get(0).getChannelOrderId());
        assertEquals(order.getChannelId(), list.get(0).getChannelId());
        assertEquals(order.getClient(), list.get(0).getClient());
        assertEquals(order.getStatus(), list.get(0).getStatus());
        assertEquals(order.getCustomer(), list.get(0).getCustomer());

    }

    @Test
    public void testSelectByChannelOrderId() {
        PartyPojo client = new PartyPojo();
        client.setName("assure");
        client.setType(PartyType.CLIENT);
        partyDao.insert(client);
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
        OrderPojo list = orderDao.selectByChannelOrderId("channelorderid");
        assertEquals(order.getChannelOrderId(), list.getChannelOrderId());
        assertEquals(Long.valueOf(1), list.getChannelId());
        assertEquals(order.getClient(), list.getClient());
        assertEquals(order.getStatus(), list.getStatus());
        assertEquals(order.getCustomer(), list.getCustomer());

    }

    @Test
    public void testSelectAll() {
        PartyPojo client = new PartyPojo();
        client.setName("assure");
        client.setType(PartyType.CLIENT);
        partyDao.insert(client);
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
        PartyPojo client1 = new PartyPojo();
        client1.setName("assure1");
        client1.setType(PartyType.CLIENT);
        partyDao.insert(client1);
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

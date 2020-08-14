package com.increff.assure.dto;

import com.increff.assure.dao.ChannelDao;
import com.increff.assure.dao.PartyDao;
import com.increff.assure.pojo.ChannelPojo;
import com.increff.assure.pojo.OrderPojo;
import com.increff.assure.pojo.PartyPojo;
import com.increff.assure.service.AbstractUnitTest;
import com.increff.assure.service.ApiException;
import com.increff.assure.service.OrderService;
import com.increff.commons.data.OrderData;
import com.increff.commons.enums.InvoiceType;
import com.increff.commons.enums.OrderStatus;
import com.increff.commons.enums.PartyType;
import com.increff.commons.form.OrderForm;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class OrderDtoTest extends AbstractUnitTest {

    @Autowired
    private PartyDao partyDao;

    @Autowired
    private OrderDto orderDto;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ChannelDao channelDao;

	@Test
	public void testAdd() throws ApiException {

		PartyPojo client = new PartyPojo();
		client.setName("assure");
		client.setType(PartyType.CLIENT);

		partyDao.insert(client);

		PartyPojo customer = new PartyPojo();
		customer.setName("customer");
		customer.setType(PartyType.CUSTOMER);

		partyDao.insert(customer);

        ChannelPojo channel = new ChannelPojo();
        channel.setName("channel");
        channel.setType(InvoiceType.CHANNEL);
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
        OrderPojo pojo=orderService.add(order);
        OrderData list = orderDto.get(pojo.getId());
        assertEquals(order.getChannelOrderId(), list.getChannelOrderId());
        assertEquals(order.getChannelId(), list.getChannelId());
        assertEquals(order.getCustomer().getName(), list.getCustomerName());

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
        List<OrderData> list = orderDto.getAll();
        assertEquals(order.getChannelOrderId(), list.get(0).getChannelOrderId());
        assertEquals(order.getChannelId(), list.get(0).getChannelId());
        assertEquals(order.getCustomer().getName(), list.get(0).getCustomerName());

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
        List<OrderData> list = orderDto.getExternalOrders();
        assertEquals(order.getChannelOrderId(), list.get(0).getChannelOrderId());
        assertEquals(order.getChannelId(), list.get(0).getChannelId());
        assertEquals(order.getCustomer().getName(), list.get(0).getCustomerName());
    }

    @Test(expected = ApiException.class)
    public void testDelete() throws ApiException {
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
        OrderPojo pojo=orderService.add(order);

        orderDto.delete(pojo.getId());
        orderDto.get(pojo.getId());

    }


}

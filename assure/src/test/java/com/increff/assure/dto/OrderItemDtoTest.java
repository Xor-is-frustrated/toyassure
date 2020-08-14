package com.increff.assure.dto;

import com.increff.assure.dao.ChannelDao;
import com.increff.assure.dao.OrderDao;
import com.increff.assure.dao.PartyDao;
import com.increff.assure.dao.ProductDao;
import com.increff.assure.pojo.ChannelPojo;
import com.increff.assure.pojo.OrderPojo;
import com.increff.assure.pojo.PartyPojo;
import com.increff.assure.pojo.ProductPojo;
import com.increff.assure.service.AbstractUnitTest;
import com.increff.assure.service.ApiException;
import com.increff.commons.data.OrderItemData;
import com.increff.commons.enums.InvoiceType;
import com.increff.commons.enums.OrderStatus;
import com.increff.commons.enums.PartyType;
import com.increff.commons.form.OrderItemFormCSV;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class OrderItemDtoTest extends AbstractUnitTest {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private PartyDao partyDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ChannelDao channelDao;

    @Autowired
    private OrderItemDto orderItemDto;

    @Test
    public void test() {
    }

    @Test
    public void testAddByAssure() throws ApiException {
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
        OrderItemFormCSV item = new OrderItemFormCSV();
        item.setOrderId(order.getId());
        item.setClientSkuId("Partysku");
        item.setOrderedQuantity(Long.valueOf(100));
        item.setSellingPrice(1.1);
        OrderItemData list = orderItemDto.addByAssure(item);
        assertEquals(item.getOrderId(), list.getOrderId());
        assertEquals(Long.valueOf(100), list.getOrderedQuantity());

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
        OrderItemFormCSV item = new OrderItemFormCSV();
        item.setOrderId(order.getId());
        item.setClientSkuId("Partysku");
        item.setOrderedQuantity(Long.valueOf(100));
        item.setSellingPrice(1.1);
        OrderItemData data = orderItemDto.addByAssure(item);
        OrderItemData list = orderItemDto.get(data.getId());
        assertEquals(data.getOrderId(), list.getOrderId());
        assertEquals(data.getProductName(), list.getProductName());
        assertEquals(data.getGlobalSkuId(), list.getGlobalSkuId());
        assertEquals(Long.valueOf(100), list.getOrderedQuantity());

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
        OrderItemFormCSV item = new OrderItemFormCSV();
        item.setOrderId(order.getId());
        item.setClientSkuId("Partysku");
        item.setOrderedQuantity(Long.valueOf(100));
        item.setSellingPrice(1.1);
        OrderItemData data = orderItemDto.addByAssure(item);
        List<OrderItemData> list = orderItemDto.getAll();
        assertEquals(data.getOrderId(), list.get(0).getOrderId());
        assertEquals(data.getProductName(), list.get(0).getProductName());
        assertEquals(data.getGlobalSkuId(), list.get(0).getGlobalSkuId());
        assertEquals(Long.valueOf(100), list.get(0).getOrderedQuantity());
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
        OrderItemFormCSV item = new OrderItemFormCSV();
        item.setOrderId(order.getId());
        item.setClientSkuId("Partysku");
        item.setOrderedQuantity(Long.valueOf(100));
        item.setSellingPrice(1.1);
        OrderItemData data = orderItemDto.addByAssure(item);
        orderItemDto.delete(data.getId());
        OrderItemData list = orderItemDto.get(data.getId());

    }

}

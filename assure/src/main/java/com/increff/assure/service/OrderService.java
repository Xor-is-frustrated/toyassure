package com.increff.assure.service;

import java.util.List;

import com.increff.assure.dao.ChannelDao;
import com.increff.assure.dao.OrderDao;
import com.increff.assure.pojo.ChannelPojo;
import com.increff.assure.pojo.PartyPojo;
import com.increff.commons.data.ChannelData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.increff.assure.dao.PartyDao;

import com.increff.commons.enums.PartyType;
import com.increff.assure.pojo.OrderPojo;
import com.increff.commons.enums.OrderStatus;

@Service
public class OrderService extends AbstractService {

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private PartyDao partyDao;

	@Autowired
	private ChannelDao channelDao;

	@Transactional(rollbackFor = ApiException.class)
	public OrderPojo add(OrderPojo p) throws ApiException {

		PartyPojo client = partyDao.selectByName(p.getClient().getName());
		checkNotNull(client, "Client name does not exist: "+p.getClient().getName());

		if (client.getType() != PartyType.CLIENT) {
			throw new ApiException("Not a client."+p.getClient().getName());
		}

		PartyPojo customer = partyDao.select(p.getCustomer().getId());
		checkNotNull(customer, "Customer name does not exist: "+p.getCustomer().getName());

		if (customer.getType() != PartyType.CUSTOMER) {
			throw new ApiException("Not a customer: "+p.getCustomer().getName());
		}

		ChannelPojo channel = channelDao.select(p.getChannelId());
		checkNotNull(channel, "Channel ID does not exist: "+p.getChannelId());

		checkZero(p.getChannelOrderId().length(), "Channel Order Id cannot be empty.");

		OrderPojo order = orderDao.selectByChannelOrderId(p.getChannelOrderId());
		checkNull(order, "Channel Order Id already exists: "+p.getChannelOrderId());

		OrderPojo orderPojo = orderDao.insert(p);
		return orderPojo;
	}

	@Transactional(readOnly = true)
	public OrderPojo get(Long id) throws ApiException {
		OrderPojo p = orderDao.select(id);
		checkNotNull(p, "Order ID does not exist: "+id);
		return p;
	}

	@Transactional(readOnly = true)
	public OrderPojo getByChannelOrderId(String channelOrderId) throws ApiException {
		OrderPojo p = orderDao.selectByChannelOrderId(channelOrderId);
		checkNotNull(p, "Channel Order ID does not exist: "+channelOrderId);
		return p;
	}

	@Transactional(readOnly = true)
	public List<OrderPojo> getAll() {
		return orderDao.selectAll();
	}

	@Transactional(rollbackFor = ApiException.class)
	public OrderPojo updateStatus(OrderPojo order, OrderStatus status) throws ApiException {
		OrderPojo p = orderDao.select(order.getId());
		checkNotNull(p, "Order ID does not exist: "+order.getId());

		p.setStatus(status);
		return p;
	}

	public List<OrderPojo> getExternalOrders(Long id) {
		return orderDao.selectExternalOrders(id);
	}

	public List<OrderPojo> getByChannelId(Long id) {
		return orderDao.selectByChannelId(id);
	}

	public List<OrderPojo> getByOrderStatus(OrderStatus status) {
		return orderDao.selectByStatus(status);
	}

	public List<OrderPojo> getBySearch(Long id,OrderStatus status) {
		return orderDao.selectByParams(id,status);
	}

	@Transactional(rollbackFor = ApiException.class)
	public void delete(Long id) {
		orderDao.delete(id);
	}

	public List<OrderPojo> getByOrderStatusExternal(OrderStatus status) {
		ChannelPojo pojo=channelDao.selectByName("INTERNAL");
		return orderDao.selectByStatusExternal(status,pojo.getId());
	}
}

package com.increff.assure.service;

import java.util.List;

import com.increff.assure.channel.Channel;
import com.increff.assure.dao.OrderDao;
import com.increff.assure.pojo.ClientPojo;
import com.increff.commons.data.ChannelData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.increff.assure.dao.ClientDao;

import com.increff.commons.enums.ClientType;
import com.increff.assure.pojo.OrderPojo;
import com.increff.commons.enums.OrderStatus;

@Service
public class OrderService extends AbstractService {

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private ClientDao clientDao;

	@Autowired
	private Channel channelDao;

	@Transactional(rollbackFor = ApiException.class)
	public OrderPojo add(OrderPojo p) throws ApiException {

		ClientPojo client = clientDao.select(p.getClient().getId());
		checkNotNull(client, "Client name does not exist");

		if (client.getType() == ClientType.CUSTOMER) {
			throw new ApiException("Client name does not exist");
		}

		ClientPojo customer = clientDao.select(p.getCustomer().getId());
		checkNotNull(customer, "Customer name does not exist");

		if (customer.getType() == ClientType.CLIENT) {
			throw new ApiException("Customer name does not exist.");
		}


		ChannelData channel = channelDao.selectChannel(p.getChannelId());
		checkNotNull(channel, "Channel ID does not exist");

		checkZero(p.getChannelOrderId().length(), "Channel Order Id cannot be empty.");

		OrderPojo order = orderDao.selectByChannelOrderId(p.getChannelOrderId());
		checkNull(order, "Channel Order Id already exists");

		OrderPojo orderPojo = orderDao.insert(p);
		return orderPojo;
	}

	@Transactional(readOnly = true)
	public OrderPojo get(Long id) throws ApiException {
		OrderPojo p = orderDao.select(id);
		checkNotNull(p, "Order ID does not exist");
		return p;
	}

	@Transactional(readOnly = true)
	public OrderPojo getByChannelOrderId(String channelOrderId) throws ApiException {
		OrderPojo p = orderDao.selectByChannelOrderId(channelOrderId);
		checkNotNull(p, "Channel Order ID does not exist");
		return p;
	}

	@Transactional(readOnly = true)
	public List<OrderPojo> getAll() {
		return orderDao.selectAll();
	}

	@Transactional(readOnly = true)
	public OrderPojo updateStatus(OrderPojo order, OrderStatus status) throws ApiException {
		OrderPojo p = orderDao.select(order.getId());
		checkNotNull(p, "Order ID does not exist");

		p.setStatus(status);
		return p;
	}

	public List<OrderPojo> getInternalOrders() {
		return orderDao.selectInternalOrders();
	}

	public List<OrderPojo> getExternalOrders() {
		return orderDao.selectExternalOrders();
	}

	@Transactional(rollbackFor = ApiException.class)
	public void delete(Long id) {
		orderDao.delete(id);
	}

}

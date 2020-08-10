package com.increff.assure.service;

import java.util.List;

import com.increff.assure.dao.OrderDao;
import com.increff.assure.dao.OrderItemDao;
import com.increff.assure.dao.ProductDao;
import com.increff.assure.pojo.OrderItemPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.increff.assure.pojo.OrderPojo;
import com.increff.commons.enums.OrderStatus;
import com.increff.assure.pojo.ProductPojo;

@Service
public class OrderItemService extends AbstractService {

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private OrderItemDao orderItemDao;
	
	@Autowired
	private ProductDao productDao;

	@Transactional(rollbackFor = ApiException.class)
	public OrderItemPojo add(OrderItemPojo p) throws ApiException {

		checkPositive(p.getSellingPrice(), "Selling Price cannot be less than zero");
		checkPositive(p.getAllocatedQuantity(), "Allocated Quantity cannot be less than zero");
		checkPositive(p.getFulfilledQuantity(), "Fulfilled Quantity cannot be less than zero");
		checkPositive(p.getOrderedQuantity(), "Ordered Quantity cannot be less than or equal to zero");

		OrderPojo order = orderDao.select(p.getOrder().getId());
		checkNotNull(order, "Order ID does not exist");


		ProductPojo product= productDao.select(p.getProduct().getGlobalSkuId());
		checkNotNull(product, "product ID does not exist");
		
		OrderItemPojo item = orderItemDao.selectByOrderAndProduct(p.getOrder(), p.getProduct());
		checkNull(item, "Product already exists");

		OrderItemPojo orderItemPojo = orderItemDao.insert(p);
		return orderItemPojo;
	}

	@Transactional(readOnly = true)
	public OrderItemPojo get(Long id) throws ApiException {
		OrderItemPojo p = orderItemDao.select(id);
		checkNotNull(p, "Order Item ID does not exist");
		return p;
	}

	@Transactional(readOnly = true)
	public List<OrderItemPojo> getAll() {
		return orderItemDao.selectAll();
	}
	
	@Transactional(readOnly = true)
	public List<OrderItemPojo> getByOrder(OrderPojo order) throws ApiException {
		OrderPojo ex = orderDao.select(order.getId());
		checkNotNull(ex, "Order ID does not exist");
		return orderItemDao.selectByOrder(order);
	}
	
	@Transactional(rollbackFor = ApiException.class)
	public OrderItemPojo update(Long id, Long orderedQuantity,Long allocatedQuantity, Long fulfilledQuantity, double sellingPrice) throws ApiException {
		checkPositive(sellingPrice, "Selling Price cannot be less than zero");
		checkPositive(orderedQuantity, "Ordered Quantity cannot be less than zero");
		checkPositive(allocatedQuantity, "Allocated Quantity cannot be less than zero");
		checkPositive(fulfilledQuantity, "Fulfilled Quantity cannot be less than zero");

		OrderItemPojo existing = orderItemDao.select(id);
		checkNotNull(existing, "Order Item ID does not exist");

		existing.setSellingPrice(sellingPrice);
		existing.setOrderedQuantity(orderedQuantity);
		existing.setFulfilledQuantity(fulfilledQuantity);
		existing.setAllocatedQuantity(allocatedQuantity);
				
		return existing;

	}
	
	@Transactional(rollbackFor = ApiException.class)
	public void delete(Long id) throws ApiException {

		OrderItemPojo p = orderItemDao.select(id);
		checkNotNull(p, "OrderItem ID does not exist");

		if (p.getOrder().getStatus() == OrderStatus.FULFILLED) {
			throw new ApiException("Order is fulfilled. Cannot delete items."); 
		}
		orderItemDao.delete(id);
	}

}

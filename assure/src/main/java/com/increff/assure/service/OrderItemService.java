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
		checkPositiveAndNotZero(p.getOrderedQuantity(), "Ordered Quantity cannot be less than or equal to zero");

		OrderPojo order = orderDao.select(p.getOrder().getId());
		checkNotNull(order, "Order ID does not exist. Id: "+p.getOrder().getId());


		ProductPojo product= productDao.select(p.getProduct().getGlobalSkuId());
		checkNotNull(product, "Global Sku ID does not exist: "+p.getProduct().getGlobalSkuId());
		
		OrderItemPojo item = orderItemDao.selectByOrderIdAndGlobalSkuId(p.getOrder().getId(), p.getProduct().getGlobalSkuId());
		checkNull(item, "Global Sku Id already exists: "+p.getProduct().getGlobalSkuId());

		OrderItemPojo orderItemPojo = orderItemDao.insert(p);
		return orderItemPojo;
	}

	@Transactional(readOnly = true)
	public OrderItemPojo get(Long id) throws ApiException {
		OrderItemPojo p = orderItemDao.select(id);
		checkNotNull(p, "Order Item ID does not exist. Id: "+id);
		return p;
	}

	@Transactional(readOnly = true)
	public List<OrderItemPojo> getAll() {
		return orderItemDao.selectAll();
	}
	
	@Transactional(readOnly = true)
	public List<OrderItemPojo> getByOrder(Long orderId) throws ApiException {
		OrderPojo ex = orderDao.select(orderId);
		checkNotNull(ex, "Order ID does not exist. Id: "+orderId);
		return orderItemDao.selectByOrderId(orderId);
	}
	
	@Transactional(rollbackFor = ApiException.class)
	public OrderItemPojo update(Long id, Long orderedQuantity,Long allocatedQuantity, Long fulfilledQuantity, double sellingPrice) throws ApiException {
		checkPositive(sellingPrice, "Selling Price cannot be less than zero. Given "+sellingPrice);
		checkPositive(orderedQuantity, "Ordered Quantity cannot be less than zero. Given "+orderedQuantity);
		checkPositive(allocatedQuantity, "Allocated Quantity cannot be less than zero. Given "+allocatedQuantity);
		checkPositive(fulfilledQuantity, "Fulfilled Quantity cannot be less than zero. Given "+fulfilledQuantity);

		OrderItemPojo existing = orderItemDao.select(id);
		checkNotNull(existing, "Order Item ID does not exist. Id: "+id);

		existing.setSellingPrice(sellingPrice);
		existing.setOrderedQuantity(orderedQuantity);
		existing.setFulfilledQuantity(fulfilledQuantity);
		existing.setAllocatedQuantity(allocatedQuantity);
				
		return existing;

	}
	
	@Transactional(rollbackFor = ApiException.class)
	public void delete(Long id) throws ApiException {

		OrderItemPojo p = orderItemDao.select(id);
		checkNotNull(p, "OrderItem ID does not exist. Id: "+id);

		if (p.getOrder().getStatus() != OrderStatus.CREATED) {
			throw new ApiException("Order is Processed. Cannot delete items.");
		}
		orderItemDao.delete(id);
	}

}

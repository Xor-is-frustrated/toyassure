package com.increff.assure.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.increff.assure.model.OrderItemData;
import com.increff.assure.model.OrderItemFormCSV;
import com.increff.assure.model.OrderItemFormChannel;
import com.increff.assure.pojo.InventoryPojo;
import com.increff.assure.pojo.OrderItemPojo;
import com.increff.assure.pojo.OrderPojo;
import com.increff.assure.pojo.OrderStatus;
import com.increff.assure.pojo.ProductPojo;
import com.increff.assure.service.ApiException;
import com.increff.assure.service.BinSkuService;
import com.increff.assure.service.ChannelListingService;
import com.increff.assure.service.InventoryService;
import com.increff.assure.service.OrderItemService;
import com.increff.assure.service.OrderService;
import com.increff.assure.service.ProductService;
import com.increff.assure.util.ConvertorUtil;

@Service
public class OrderItemDto {

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderItemService orderItemService;

	@Autowired
	private ProductService productService;

	@Autowired
	private ChannelListingService channelListingService;

	@Autowired
	private InventoryService inventoryService;

	@Autowired
	private BinSkuService binSkuService;

	public OrderItemData addByAssure(OrderItemFormCSV form) throws ApiException {
		OrderPojo order = orderService.get(form.getOrderId());
		ProductPojo product = productService.getByClientIdAndClientSkuId(form.getClientSkuId(), order.getClient());
		OrderItemPojo pojo = ConvertorUtil.convert(form, order, product);
		OrderItemPojo orderItem = orderItemService.add(pojo);
		return ConvertorUtil.convert(orderItem);
	}

	public OrderItemData addByChannel(OrderItemFormChannel form) throws ApiException {
		OrderPojo order = orderService.get(form.getOrderId());
		ProductPojo product = channelListingService
				.getByChannelSkuIdAndChannel(form.getChannelSkuId(), order.getChannel()).getProduct();
		OrderItemPojo pojo = ConvertorUtil.convert(form, order, product);
		OrderItemPojo orderItem = orderItemService.add(pojo);
		return ConvertorUtil.convert(orderItem);
	}

	public OrderItemData get(Long id) throws ApiException {
		OrderItemPojo pojo = orderItemService.get(id);
		return ConvertorUtil.convert(pojo);
	}

	public List<OrderItemData> getAll() {
		List<OrderItemPojo> list = orderItemService.getAll();
		return ConvertorUtil.convertOrderItems(list);
	}

	public List<OrderItemData> getByOrder(Long id) throws ApiException {
		OrderPojo order = orderService.get(id);
		List<OrderItemPojo> list = orderItemService.getByOrder(order);
		return ConvertorUtil.convertOrderItems(list);
	}

	public OrderItemData update(Long id, OrderItemFormChannel form) throws ApiException {
		OrderItemPojo pojo = orderItemService.get(id);
		OrderItemPojo list = orderItemService.update(id, form.getOrderedQuantity(), pojo.getAllocatedQuantity(),
				pojo.getFulfilledQuantity(), form.getSellingPrice());
		return ConvertorUtil.convert(list);
	}

	@Transactional(rollbackFor = ApiException.class)
	public List<OrderItemData> allocate(Long id) throws ApiException {

		OrderPojo order = orderService.get(id);
		List<OrderItemPojo> itemList = orderItemService.getByOrder(order);

		boolean allItemsAllocated = true;
		// For every item in order, reducing the availableQuantity of the inventory and
		// increasing the value to allocatedQuantity. Reducing the binskus.
		for (OrderItemPojo item : itemList) {
			InventoryPojo inventory = inventoryService.getByProduct(item.getProduct());
			Long allocateQuantity = Math.min(inventory.getAvailableQuantity(),
					item.getOrderedQuantity() - item.getAllocatedQuantity());
			Long availableQuantity = inventory.getAvailableQuantity() - allocateQuantity;

			inventoryService.updateQuantities(inventory.getId(), availableQuantity,
					allocateQuantity + inventory.getAllocatedQuantity(), inventory.getFulfilledQuantity());

			orderItemService.update(item.getId(), item.getOrderedQuantity(),
					item.getAllocatedQuantity() + allocateQuantity, item.getFulfilledQuantity(),
					item.getSellingPrice());

			binSkuService.reduceBinSkuByAllocatedQuantity(item.getProduct(), allocateQuantity);
			if (item.getOrderedQuantity()-item.getAllocatedQuantity() != allocateQuantity) {
				allItemsAllocated = false;
			}
		}

		if (allItemsAllocated == true) {
			orderService.updateStatus(order, OrderStatus.ALLOCATED);
		}

		return getByOrder(id);
	}

	@Transactional(rollbackFor = ApiException.class)
	public List<OrderItemData> fulfillAndGenerateInvoice(Long id) throws ApiException {
		OrderPojo order = orderService.get(id);
		if (order.getStatus() == OrderStatus.CREATED) {
			throw new ApiException("Order items are not allocated.");
		}

		List<OrderItemPojo> itemList = orderItemService.getByOrder(order);
		for (OrderItemPojo item : itemList) {
			InventoryPojo inventory = inventoryService.getByProduct(item.getProduct());
			Long allocateQuantity = inventory.getAllocatedQuantity() - item.getOrderedQuantity();
			Long fulfilledQuantity = inventory.getFulfilledQuantity() + allocateQuantity;

			inventoryService.updateQuantities(inventory.getId(), inventory.getAvailableQuantity(), allocateQuantity,
					fulfilledQuantity);

			orderItemService.update(item.getId(), item.getOrderedQuantity(), Long.valueOf(0), item.getOrderedQuantity(),
					item.getSellingPrice());

		}

		return getByOrder(id);

	}

}

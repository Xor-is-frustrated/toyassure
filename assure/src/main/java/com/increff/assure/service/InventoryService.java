package com.increff.assure.service;

import java.util.List;

import com.increff.assure.dao.ProductDao;
import com.increff.assure.pojo.InventoryPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.increff.assure.dao.InventoryDao;
import com.increff.assure.pojo.ProductPojo;

@Service
public class InventoryService extends AbstractService {

	@Autowired
	private InventoryDao dao;

	@Autowired
	private ProductDao productDao;

	@Transactional(rollbackFor = ApiException.class)
	public InventoryPojo add(InventoryPojo p) throws ApiException {

		ProductPojo pojo = productDao.select(p.getProduct().getGlobalSkuId());
		checkNotNull(pojo, "GlobalSkuId does not exist");
		
		Long alloctedQuantity = p.getAllocatedQuantity();
		checkPositive(alloctedQuantity, "Allocated quantity cannot be less than zero");
		Long fulfilledQuantity = p.getFulfilledQuantity();
		checkPositive(fulfilledQuantity, " Fulfilled quantity cannot be less than zero");
		Long availableQuantity = p.getAvailableQuantity();
		checkPositive(availableQuantity, "Available quantity cannot be less than zero");
		
		InventoryPojo inv = dao.selectByProduct(pojo);		
		if(inv!=null) {
			availableQuantity+=inv.getAvailableQuantity();
			fulfilledQuantity+=inv.getFulfilledQuantity();
			alloctedQuantity+=inv.getAllocatedQuantity();
			return updateQuantities(inv.getId(),availableQuantity,alloctedQuantity,fulfilledQuantity);
		}
		else {	
			return dao.insert(p);
		}
	}

	@Transactional(readOnly = true)
	public InventoryPojo get(Long id) throws ApiException {
		InventoryPojo p = dao.select(id);
		checkNotNull(p, "Inventory ID does not exist");

		return p;
	}

	@Transactional(readOnly = true)
	public InventoryPojo getByProduct(ProductPojo pojo) throws ApiException {

		ProductPojo product = productDao.select(pojo.getGlobalSkuId());
		checkNotNull(product, "Product ID does not exist");

		InventoryPojo inv = dao.selectByProduct(product);
		return inv;
	}

	@Transactional(readOnly = true)
	public List<InventoryPojo> getAll() {
		return dao.selectAll();
	}

	@Transactional(rollbackFor = ApiException.class)
	public InventoryPojo updateQuantities(Long id, Long availableQuantity, Long allocatedQuantity, Long fulfilledQuantity) throws ApiException {

		checkPositive(availableQuantity, "Available quantity cannot be less than zero");
		checkPositive(allocatedQuantity, "Allocated quantity cannot be less than zero");
		checkPositive(fulfilledQuantity, "Fulfilled quantity cannot be less than zero");

		InventoryPojo p = dao.select(id);
		checkNotNull(p, "Inventory ID does not exist");

		p.setAllocatedQuantity(allocatedQuantity);
		p.setAvailableQuantity(availableQuantity);
		p.setFulfilledQuantity(fulfilledQuantity);
		return p;
	}

	
}

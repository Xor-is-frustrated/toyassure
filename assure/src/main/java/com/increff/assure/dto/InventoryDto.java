package com.increff.assure.dto;

import java.util.List;

import com.increff.commons.data.InventoryData;
import com.increff.commons.form.InventoryForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.increff.assure.pojo.InventoryPojo;
import com.increff.assure.pojo.ProductPojo;
import com.increff.assure.service.ApiException;
import com.increff.assure.service.InventoryService;
import com.increff.assure.service.ProductService;
import com.increff.assure.util.ConvertorUtil;


@Service
public class InventoryDto { 

	@Autowired
	private InventoryService inventoryService;
	
	@Autowired
	private ProductService productService;


	public InventoryData add(InventoryForm form) throws ApiException {
		ProductPojo pojo = productService.get(form.getGlobalSkuId());
		InventoryPojo inv = ConvertorUtil.convert(form, pojo);
		inv = inventoryService.add(inv);
		return ConvertorUtil.convert(inv);
	}

	public InventoryData getByGlobalSkuId(Long globalSkuId) throws ApiException {
		InventoryPojo pojo = inventoryService.getByGlobalSkuId(globalSkuId);
		return ConvertorUtil.convert(pojo);
	}

	public List<InventoryData> getAll() {
		List<InventoryPojo> list = inventoryService.getAll();
		return ConvertorUtil.convertInventories(list);

	}

	public List<InventoryData> getByClientName(String name) {
		List<InventoryPojo> list = inventoryService.getByClientName(name);
		return ConvertorUtil.convertInventories(list);

	}

	public InventoryData update(Long globalSkuId, InventoryForm form) throws ApiException {
		ProductPojo pojo = productService.get(form.getGlobalSkuId());
		InventoryPojo inv= inventoryService.getByGlobalSkuId(pojo.getGlobalSkuId());
		Long availableQuantity = form.getAvailableQuantity();
		Long allocatedQuantity = form.getAllocatedQuantity();
		Long fulfilledQuantity = form.getFulfilledQuantity();
		inv=inventoryService.updateQuantities(inv.getProduct().getGlobalSkuId(), availableQuantity,
				allocatedQuantity, fulfilledQuantity);
		return ConvertorUtil.convert(inv);
	}

	
}

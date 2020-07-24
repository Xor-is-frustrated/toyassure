package com.increff.assure.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.increff.assure.model.BinSkuData;
import com.increff.assure.model.BinSkuForm;
import com.increff.assure.pojo.BinPojo;
import com.increff.assure.pojo.BinSkuPojo;
import com.increff.assure.pojo.ClientPojo;
import com.increff.assure.pojo.ProductPojo;
import com.increff.assure.service.ApiException;
import com.increff.assure.service.BinService;
import com.increff.assure.service.BinSkuService;
import com.increff.assure.service.ClientService;
import com.increff.assure.service.ProductService;
import com.increff.assure.util.ConvertorUtil;

@Service
public class BinSkuDto {

	@Autowired
	private ProductService productService; 
	
	@Autowired
	private ClientService clientService; 
	
	@Autowired
	private BinService binService;
	
	@Autowired
	private BinSkuService binSkuService;

	public BinSkuData add(BinSkuForm form) throws ApiException {
		
		ClientPojo client= clientService.get(form.getClientId());
		ProductPojo product = productService.getByClientIdAndClientSkuId(form.getClientSkuId(),client);
		BinPojo bin= binService.get(form.getBinId());
		
		BinSkuPojo pojo= ConvertorUtil.convert(form,product,bin);
		pojo = binSkuService.add(pojo);
		return ConvertorUtil.convert(pojo);         
	}

	public BinSkuData get(Long id) throws ApiException {
		BinSkuPojo pojo = binSkuService.get(id);
		return ConvertorUtil.convert(pojo);
	}

	public List<BinSkuData> getAll() {
		List<BinSkuPojo> list = binSkuService.getAll();
		return ConvertorUtil.convertBinSkus(list);
	}
	
	public void update(Long id, Long quantity) throws ApiException {
		binSkuService.updateQuantity(id, quantity);
	}
	
}

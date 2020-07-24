package com.increff.assure.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.increff.assure.model.ProductData;
import com.increff.assure.model.ProductForm;
import com.increff.assure.pojo.ClientPojo;
import com.increff.assure.pojo.ProductPojo;
import com.increff.assure.service.ApiException;
import com.increff.assure.service.ClientService;
import com.increff.assure.service.ProductService;
import com.increff.assure.util.ConvertorUtil;

@Service
public class ProductDto {

	@Autowired
	private ProductService productService; 
	
	@Autowired
	private ClientService clientService; 

	public ProductData add(ProductForm form) throws ApiException {
		
		ClientPojo client= clientService.get(form.getClientId());
		
		ProductPojo pojo = ConvertorUtil.convert(form,client);
		ProductPojo product = productService.add(pojo);
		return ConvertorUtil.convert(product);         
	}

	public ProductData get(Long id) throws ApiException {
		ProductPojo pojo = productService.get(id);
		return ConvertorUtil.convert(pojo);
	}

	public List<ProductData> getAll() {
		List<ProductPojo> list = productService.getAll();
		return ConvertorUtil.convertProducts(list);
	}
	
	public void update(Long id, ProductForm form) throws ApiException {
		ClientPojo client= clientService.get(form.getClientId());
		ProductPojo productPojo = ConvertorUtil.convert(form, client);
		productService.update(id, productPojo);

	}
	
}

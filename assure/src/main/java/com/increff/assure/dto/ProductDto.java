package com.increff.assure.dto;

import java.util.List;

import com.increff.assure.pojo.PartyPojo;
import com.increff.assure.service.ApiException;
import com.increff.assure.service.ProductService;
import com.increff.assure.util.ConvertorUtil;
import com.increff.commons.data.ProductData;
import com.increff.commons.form.ProductForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.increff.assure.pojo.ProductPojo;
import com.increff.assure.service.PartyService;

@Service
public class ProductDto {    

	@Autowired
	private ProductService productService;
	
	@Autowired
	private PartyService partyService;

	public ProductData add(ProductForm form) throws ApiException {
		
		PartyPojo client= partyService.getByName(form.getClientName());
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

	public List<ProductData> getByClientName(String name) {
		List<ProductPojo> list = productService.getByClientName(name);
		return ConvertorUtil.convertProducts(list);
	}
	
	public void update(Long id, ProductForm form) throws ApiException {
		PartyPojo client= partyService.getByName(form.getClientName());
		ProductPojo pojo = ConvertorUtil.convert(form, client);
		productService.update(id, pojo.getName(),pojo.getDescription(), pojo.getMrp(), pojo.getBrandId());

	}

    public ProductData getByClientNameAndClientSkuId(ProductForm form) throws ApiException {
		PartyPojo client= partyService.getByName(form.getClientName());
		String clientSkuId= form.getClientSkuId();
		ProductPojo pojo= productService.getByClientIdAndClientSkuId(clientSkuId,client.getId());
		return ConvertorUtil.convert(pojo);
    }
}

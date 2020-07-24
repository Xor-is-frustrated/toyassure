package com.increff.assure.util;

import java.util.ArrayList;
import java.util.List;

import com.increff.assure.model.ClientData;
import com.increff.assure.model.ClientForm;
import com.increff.assure.model.ProductData;
import com.increff.assure.model.ProductForm;
import com.increff.assure.pojo.ClientPojo;
import com.increff.assure.pojo.ProductPojo;

public class ConvertorUtil {

	public static ClientPojo convert(ClientForm form) {
		ClientPojo client=new ClientPojo();
		client.setName(form.getName());
		client.setType(form.getType());
		return client;
	}

	public static ClientData convert(ClientPojo pojo) {
		ClientData client = new ClientData();
		client.setId(pojo.getId());
		client.setName(pojo.getName());
		client.setType(pojo.getType());
		return client;
	}

	public static List<ClientData> convertClients(List<ClientPojo> pojos) {
		List<ClientData>list = new ArrayList<ClientData>();
		for(ClientPojo pojo:pojos) {
			list.add(convert(pojo));
		}
		return list;
	}

	public static ProductPojo convert(ProductForm form, ClientPojo client) {
		ProductPojo pojo= new ProductPojo();
		pojo.setBrandId(form.getBrandId());
		pojo.setClient(client);
		pojo.setDescription(form.getDescription());
		pojo.setClientSkuId(form.getClientSkuId());
		pojo.setMrp(form.getMrp());
		pojo.setName(form.getName());
		return pojo;
	}

	public static ProductData convert(ProductPojo product) {
		ProductData data= new ProductData();
		data.setGlobalSkuId(product.getGlobalSkuId());
		data.setBrandId(product.getBrandId());
		data.setClientId(product.getClient().getId());
		data.setDescription(product.getDescription());
		data.setClientSkuId(product.getClientSkuId());
		data.setMrp(product.getMrp());
		data.setName(product.getName());
		return data;
	}

	public static List<ProductData> convertProducts(List<ProductPojo> pojos) {
		List<ProductData>list = new ArrayList<ProductData>();
		for(ProductPojo pojo:pojos) {
			list.add(convert(pojo));
		}
		return list;
	}
	
	

	
	
}

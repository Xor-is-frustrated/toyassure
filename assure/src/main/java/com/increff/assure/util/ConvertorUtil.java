package com.increff.assure.util;

//import com.increff.assure.pojo.*;
//import com.increff.commons.data.*;
//import com.increff.commons.enums.OrderStatus;
//
//import com.increff.commons.form.*;


import com.increff.assure.pojo.*;
import com.increff.commons.data.*;
import com.increff.commons.enums.OrderStatus;
import com.increff.commons.form.*;

import java.util.ArrayList;
import java.util.List;

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
		data.setId(product.getGlobalSkuId());
		data.setBrandId(product.getBrandId());       
		data.setClientName(product.getClient().getName());
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

	public static BinData convert(BinPojo bin) {
		BinData data = new BinData();
		data.setBinId(bin.getBinId());
		return data;
	}

	public static List<BinData> convertBins(List<BinPojo> pojos) {
		List<BinData> list= new ArrayList<BinData>();
		for(BinPojo pojo:pojos) {
			list.add(convert(pojo));
		}
		return list;
	}

	public static BinSkuPojo convert(BinSkuForm form, ProductPojo product, BinPojo bin) {
		BinSkuPojo pojo = new BinSkuPojo();
		pojo.setBin(bin);
		pojo.setProduct(product);
		pojo.setQuantity(form.getQuantity());
		return pojo;
	}

	public static BinSkuData convert(BinSkuPojo pojo) {
		BinSkuData data = new BinSkuData();
		data.setGlobalSkuId(pojo.getProduct().getGlobalSkuId());
		data.setQuantity(pojo.getQuantity());
		data.setId(pojo.getId());
		data.setClientName(pojo.getProduct().getClient().getName());
		data.setBinId(pojo.getBin().getBinId());
		data.setClientSkuId(pojo.getProduct().getClientSkuId());
		data.setProductName(pojo.getProduct().getName());
		return data;
	}

	public static List<BinSkuData> convertBinSkus(List<BinSkuPojo> pojos) {
		List<BinSkuData> list= new ArrayList<BinSkuData>();
		for(BinSkuPojo pojo:pojos) {
			list.add(convert(pojo));
		}
		return list;
	}

	public static InventoryPojo convert(InventoryForm form, ProductPojo pojo) {
		InventoryPojo inv= new InventoryPojo();
		inv.setAllocatedQuantity(form.getAllocatedQuantity());
		inv.setAvailableQuantity(form.getAvailableQuantity());
		inv.setFulfilledQuantity(form.getFulfilledQuantity());
		inv.setProduct(pojo);
		return inv;
	}
	
	public static InventoryData convert(InventoryPojo pojo) {
		InventoryData data = new InventoryData();
		data.setId(pojo.getId());
		data.setAvailableQuantity(pojo.getAvailableQuantity());
		data.setAllocatedQuantity(pojo.getAllocatedQuantity());
		data.setFulfilledQuantity(pojo.getFulfilledQuantity());
		data.setGlobalSkuId(pojo.getProduct().getGlobalSkuId());
		data.setProductName(pojo.getProduct().getName());
		return data;
	}

	public static List<InventoryData> convertInventories(List<InventoryPojo> pojos) {
		List<InventoryData> list= new ArrayList<InventoryData>();
		for(InventoryPojo pojo:pojos) {
			list.add(convert(pojo));
		}
		return list;
	}



	public static OrderPojo convert(OrderForm form, ChannelData channel, ClientPojo client, ClientPojo customer) {
		OrderPojo order= new OrderPojo();
		order.setChannelId(channel.getId());
		order.setChannelOrderId(form.getChannelOrderId());
		order.setClient(client);
		order.setCustomer(customer);
		order.setStatus(OrderStatus.CREATED);
		return order;
	}

	public static OrderData convert(OrderPojo order,String channelName) {
		OrderData data= new OrderData();
//		data.setCreatedDate(order.getCreatedAt());
		data.setChannelName(channelName);
		data.setChannelId(order.getChannelId());
		data.setChannelOrderId(order.getChannelOrderId());
		data.setClientName(order.getClient().getName());
		data.setCustomerName(order.getCustomer().getName());
		data.setId(order.getId());      
		data.setStatus(order.getStatus());
		return data;
	}

	public static List<OrderData> convertOrders(List<OrderPojo> pojos,List<String>channelName) {
		List<OrderData> list= new ArrayList<OrderData>();
		for(int i=0;i<pojos.size();i++) {
			list.add(convert(pojos.get(i),channelName.get(i)));
		}
		return list;
	}

	public static OrderItemPojo convert(OrderItemFormCSV form, OrderPojo order, ProductPojo product) {
		OrderItemPojo pojo= new OrderItemPojo();
		pojo.setAllocatedQuantity(Long.valueOf(0));
		pojo.setFulfilledQuantity(Long.valueOf(0));
		pojo.setOrderedQuantity(form.getOrderedQuantity());
		pojo.setOrder(order);
		pojo.setProduct(product);
		pojo.setSellingPrice(form.getSellingPrice());
		return pojo;
	}

	public static OrderItemData convert(OrderItemPojo orderItem) {
		OrderItemData data= new OrderItemData();
		data.setId(orderItem.getId());
		data.setOrderedQuantity(orderItem.getOrderedQuantity());
		data.setAllocatedQuantity(orderItem.getAllocatedQuantity());
		data.setFulfilledQuantity(orderItem.getFulfilledQuantity());
		data.setSellingPrice(orderItem.getSellingPrice());
		data.setClientSkuId(orderItem.getProduct().getClientSkuId());
		data.setGlobalSkuId(orderItem.getProduct().getGlobalSkuId());
		data.setOrderId(orderItem.getOrder().getId()); 
		data.setProductName(orderItem.getProduct().getName());
		double cost= orderItem.getOrderedQuantity()*orderItem.getSellingPrice();
		cost = Math.round(cost * 100.0) / 100.0;
		data.setItemTotalCost(cost);
		return data;
	} 

	public static List<OrderItemData> convertOrderItems(List<OrderItemPojo> pojos) {
		List<OrderItemData> list= new ArrayList<OrderItemData>();
		for(OrderItemPojo pojo:pojos) {
			list.add(convert(pojo));
		}
		return list;
	}

	public static OrderItemPojo convert(OrderItemFormChannel form, OrderPojo order, ProductPojo product) {
		OrderItemPojo pojo= new OrderItemPojo();
		pojo.setAllocatedQuantity(Long.valueOf(0));
		pojo.setFulfilledQuantity(Long.valueOf(0));
		pojo.setOrderedQuantity(form.getOrderedQuantity());
		pojo.setOrder(order);
		pojo.setProduct(product);
		pojo.setSellingPrice(form.getSellingPrice());
		return pojo;
	} 

	
	
	
	
	

	
	
}

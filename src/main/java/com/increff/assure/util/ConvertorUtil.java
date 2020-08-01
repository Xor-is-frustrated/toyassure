package com.increff.assure.util;

import java.util.ArrayList;
import java.util.List;

import com.increff.assure.model.BinData;
import com.increff.assure.model.BinSkuData;
import com.increff.assure.model.BinSkuForm;
import com.increff.assure.model.ChannelData;
import com.increff.assure.model.ChannelForm;
import com.increff.assure.model.ChannelListingData;
import com.increff.assure.model.ChannelListingForm;
import com.increff.assure.model.ClientData;
import com.increff.assure.model.ClientForm;
import com.increff.assure.model.InventoryData;
import com.increff.assure.model.InventoryForm;
import com.increff.assure.model.OrderData;
import com.increff.assure.model.OrderForm;
import com.increff.assure.model.OrderItemData;
import com.increff.assure.model.OrderItemFormCSV;
import com.increff.assure.model.OrderItemFormChannel;
import com.increff.assure.model.ProductData;
import com.increff.assure.model.ProductForm;
import com.increff.assure.pojo.BinPojo;
import com.increff.assure.pojo.BinSkuPojo;
import com.increff.assure.pojo.ChannelListingPojo;
import com.increff.assure.pojo.ChannelPojo;
import com.increff.assure.pojo.ClientPojo;
import com.increff.assure.pojo.InventoryPojo;
import com.increff.assure.pojo.OrderItemPojo;
import com.increff.assure.pojo.OrderPojo;
import com.increff.assure.pojo.OrderStatus;
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

	public static ChannelPojo convert(ChannelForm form) {
		ChannelPojo pojo= new ChannelPojo();
		pojo.setName(form.getName());
		pojo.setType(form.getType());
		return pojo; 
	}

	public static ChannelData convert(ChannelPojo channel) {
		ChannelData pojo = new ChannelData();
		pojo.setName(channel.getName());
		pojo.setType(channel.getType());
		pojo.setId(channel.getId());
		return pojo;
		
	}
 
	public static List<ChannelData> convertChannels(List<ChannelPojo> pojos) {
		List<ChannelData> list= new ArrayList<ChannelData>();
		for(ChannelPojo pojo:pojos) {
			list.add(convert(pojo));
		}
		return list;
	}

	public static ChannelListingPojo convert(ChannelListingForm form, ClientPojo client, ProductPojo product, ChannelPojo channel) {
		ChannelListingPojo cl= new ChannelListingPojo();
		cl.setChannel(channel);
		cl.setChannelSkuId(form.getChannelSkuId());
		cl.setProduct(product);
		cl.setClient(client);
		return cl;
	}

	public static ChannelListingData convert(ChannelListingPojo cl) {
		ChannelListingData data= new ChannelListingData();
		data.setChannelName(cl.getChannel().getName());
		data.setClientName(cl.getClient().getName());
		data.setChannelSkuId(cl.getChannelSkuId());
		data.setId(cl.getId());
		data.setClientSkuId(cl.getProduct().getClientSkuId());
		data.setProductName(cl.getProduct().getName());
		return data;
	}

	public static List<ChannelListingData> convertChannelListings(List<ChannelListingPojo> pojos) {
		List<ChannelListingData> list= new ArrayList<ChannelListingData>();
		for(ChannelListingPojo pojo:pojos) {
			list.add(convert(pojo));
		}
		return list;
	}

	public static OrderPojo convert(OrderForm form, ChannelPojo channel, ClientPojo client, ClientPojo customer) {
		OrderPojo order= new OrderPojo();
		order.setChannel(channel);
		order.setChannelOrderId(form.getChannelOrderId());
		order.setClient(client);
		order.setCustomer(customer);
		order.setStatus(OrderStatus.CREATED);
		return order;
	}

	public static OrderData convert(OrderPojo order) {
		OrderData data= new OrderData();
		data.setChannelName(order.getChannel().getName());
		data.setChannelOrderId(order.getChannelOrderId());
		data.setClientName(order.getClient().getName());
		data.setCustomerName(order.getCustomer().getName());
		data.setId(order.getId());
		data.setStatus(order.getStatus());
		return data;
	}

	public static List<OrderData> convertOrders(List<OrderPojo> pojos) {
		List<OrderData> list= new ArrayList<OrderData>();
		for(OrderPojo pojo:pojos) {
			list.add(convert(pojo));
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
		data.setOrderedQuantity(orderItem.getOrderedQuantity());
		data.setAllocatedQuantity(orderItem.getAllocatedQuantity());
		data.setFulfilledQuantity(orderItem.getFulfilledQuantity());
		data.setSellingPrice(orderItem.getSellingPrice());
		data.setChannelName(orderItem.getOrder().getChannel().getName());
		data.setClientSkuId(orderItem.getProduct().getClientSkuId());
		data.setGlobalSkuId(orderItem.getProduct().getGlobalSkuId());
		data.setOrderId(orderItem.getOrder().getId());
		data.setProductName(orderItem.getProduct().getName());
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
		return pojo;
	}

	
	
	
	
	

	
	
}

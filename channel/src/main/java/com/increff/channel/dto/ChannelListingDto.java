package com.increff.channel.dto;

import com.increff.channel.assure.Client;
import com.increff.channel.assure.Product;
import com.increff.channel.pojo.ChannelListingPojo;
import com.increff.channel.pojo.ChannelPojo;
import com.increff.channel.service.ApiException;
import com.increff.channel.service.ChannelListingService;
import com.increff.channel.service.ChannelService;
import com.increff.channel.util.ConvertorUtil;
import com.increff.commons.data.ChannelListingData;
import com.increff.commons.data.ClientData;
import com.increff.commons.data.ProductData;
import com.increff.commons.form.ChannelListingForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChannelListingDto {

	@Autowired
	private ChannelListingService channelListingService;

	@Autowired
	private ChannelService channelService;

	@Autowired
	private Client clientService;

	@Autowired
	private Product productService;

	public ChannelListingData add(ChannelListingForm form) throws ApiException {
		ClientData client = clientService.getByName(form.getClientName());
		ProductData product = productService.getByClientNameAndClientSkuId(form.getClientSkuId(), client.getName());
		ChannelPojo channel = channelService.getByName(form.getChannelName());
		ChannelListingPojo pojo = ConvertorUtil.convert(form, client, product, channel);
		ChannelListingPojo cl = channelListingService.add(pojo);
		return ConvertorUtil.convert(cl,product);
	}

	public ChannelListingData get(Long id) throws ApiException {
		ChannelListingPojo pojo = channelListingService.get(id);
		ProductData data= productService.getProductData(pojo.getGlobalSkuId());
		return ConvertorUtil.convert(pojo,data);
	}

	public List<ChannelListingData> getAll() {
		List<ChannelListingPojo> list = channelListingService.getAll();
		List<ProductData>product=new ArrayList<ProductData>();
		for(ChannelListingPojo pojo:list){
			product.add(productService.getProductData(pojo.getGlobalSkuId()));
		}
		return ConvertorUtil.convertChannelListings(list,product);
	}

    public ChannelListingData getByChannelNameAndSku(ChannelListingForm form) throws ApiException{
		ChannelPojo channel = channelService.getByName(form.getChannelName());
		ChannelListingPojo pojo = channelListingService.getByChannelSkuIdAndChannel(form.getChannelSkuId(),channel);
		ProductData data= productService.getProductData(pojo.getGlobalSkuId());
		return ConvertorUtil.convert(pojo,data);
    }

	public ChannelListingData getByChannelIdAndSku(ChannelListingForm form) throws ApiException{
		ChannelPojo channel = channelService.get(form.getChannelId());
		ChannelListingPojo pojo = channelListingService.getByChannelSkuIdAndChannel(form.getChannelSkuId(),channel);
		ProductData data= productService.getProductData(pojo.getGlobalSkuId());
		return ConvertorUtil.convert(pojo,data);

	}
}

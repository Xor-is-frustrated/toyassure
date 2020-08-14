package com.increff.channel.dto;

import com.increff.channel.client.ClientAssure;
import com.increff.channel.pojo.ChannelListingPojo;
import com.increff.channel.service.ApiException;
import com.increff.channel.service.ChannelListingService;
import com.increff.channel.util.ConvertorUtil;

import com.increff.commons.data.ChannelData;
import com.increff.commons.data.ChannelListingData;
import com.increff.commons.data.PartyData;
import com.increff.commons.data.ProductData;
import com.increff.commons.enums.PartyType;
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
	private ClientAssure clientAssure;

	public void setChannelRestTemplate(ClientAssure channelRestTemplate) {
		clientAssure = channelRestTemplate;
	}


	public ChannelListingData add(ChannelListingForm form) throws ApiException {
		PartyData client = clientAssure.getClientByName(form.getClientName());
		System.out.println("1"+client.getName());
		ProductData product = clientAssure.getProductByClientNameAndClientSkuId(form.getClientSkuId(), client.getName());
		if(product==null){
			throw new ApiException("client and client sku pair does not exist");
		}
		System.out.println("2"+product.getName());
		ChannelData channel = clientAssure.getByName(form.getChannelName());
		System.out.println("3"+channel.getName());
		ChannelListingPojo cl = channelListingService.add(form, client, product, channel);
		System.out.println("4");
		return ConvertorUtil.convert(cl,product, form.getChannelName());
	}

	public ChannelListingData get(Long id) throws ApiException {
		ChannelListingPojo pojo = channelListingService.get(id);
		ProductData data= clientAssure.getProduct(pojo.getGlobalSkuId());
		String channelName = clientAssure.selectChannel(pojo.getChannelId()).getName();
		return ConvertorUtil.convert(pojo,data,channelName);
	}

	public List<ChannelListingData> getAll() {
		List<ChannelListingPojo> list = channelListingService.getAll();
		List<String>channelNames= new ArrayList<String>();
		List<ProductData>product=new ArrayList<ProductData>();
		for(ChannelListingPojo pojo:list){
			channelNames.add(clientAssure.selectChannel(pojo.getChannelId()).getName());
			product.add(clientAssure.getProduct(pojo.getGlobalSkuId()));
		}
		return ConvertorUtil.convertChannelListings(list,product,channelNames);
	}

	public ChannelListingData getByChannelIdAndSku(ChannelListingForm form) throws ApiException{
		ChannelData channel = clientAssure.selectChannel(form.getChannelId());
		ChannelListingPojo pojo = channelListingService.getByChannelSkuIdAndChannelId(form.getChannelSkuId(),channel,
				form);
		ProductData data= clientAssure.getProduct(pojo.getGlobalSkuId());
		return ConvertorUtil.convert(pojo,data,channel.getName());

	}

    public List<ChannelListingData> getByChannelName(String name) {
		System.out.println(name);
		ChannelData channel = clientAssure.getByName(name);
		System.out.println("1"+channel);
		System.out.println("1"+channel.getId()+channel.getName());
		List<ChannelListingPojo> list = channelListingService.getByChannelName(channel.getId());
		System.out.println("2"+list);
		List<String>channelNames= new ArrayList<String>();
		List<ProductData>product=new ArrayList<ProductData>();
		for(ChannelListingPojo pojo:list){
			channelNames.add(clientAssure.selectChannel(pojo.getChannelId()).getName());
			product.add(clientAssure.getProduct(pojo.getGlobalSkuId()));
		}
		System.out.println("3");
		return ConvertorUtil.convertChannelListings(list,product,channelNames);
    }
}

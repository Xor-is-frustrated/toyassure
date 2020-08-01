package com.increff.assure.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.increff.assure.model.ChannelListingData;
import com.increff.assure.model.ChannelListingForm;
import com.increff.assure.pojo.ChannelListingPojo;
import com.increff.assure.pojo.ChannelPojo;
import com.increff.assure.pojo.ClientPojo;
import com.increff.assure.pojo.ProductPojo;
import com.increff.assure.service.ApiException;
import com.increff.assure.service.ChannelListingService;
import com.increff.assure.service.ChannelService;
import com.increff.assure.service.ClientService;
import com.increff.assure.service.ProductService;
import com.increff.assure.util.ConvertorUtil;

@Service
public class ChannelListingDto {

	@Autowired
	private ChannelListingService channelListingService;
	
	@Autowired
	private ChannelService channelService;

	@Autowired
	private ClientService clientService;
	
	@Autowired
	private ProductService productService;
	
	

	public ChannelListingData add(ChannelListingForm form) throws ApiException {
		ClientPojo client = clientService.getByName(form.getClientName());
		ProductPojo product = productService.getByClientIdAndClientSkuId(form.getClientSkuId(), client);
		ChannelPojo channel = channelService.getByName(form.getChannelName());
		
		
		ChannelListingPojo pojo = ConvertorUtil.convert(form,client,product, channel);
		ChannelListingPojo cl = channelListingService.add(pojo);
		return ConvertorUtil.convert(cl);
	}

	public ChannelListingData get(Long id) throws ApiException {
		ChannelListingPojo pojo = channelListingService.get(id);
		return ConvertorUtil.convert(pojo);
	}

	public List<ChannelListingData> getAll() {
		List<ChannelListingPojo> list = channelListingService.getAll();
		return ConvertorUtil.convertChannelListings(list);
	}

}

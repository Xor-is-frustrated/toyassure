package com.increff.assure.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.increff.assure.model.ChannelData;
import com.increff.assure.model.ChannelForm;
import com.increff.assure.pojo.ChannelPojo;
import com.increff.assure.service.ApiException;
import com.increff.assure.service.ChannelService;
import com.increff.assure.util.ConvertorUtil;

@Service
public class ChannelDto {

	@Autowired
	private ChannelService channelService; 

	public ChannelData add(ChannelForm form) throws ApiException {
		ChannelPojo pojo = ConvertorUtil.convert(form);
		ChannelPojo channel = channelService.add(pojo);
		return ConvertorUtil.convert(channel);
	}

	public ChannelData get(Long id) throws ApiException {
		ChannelPojo pojo = channelService.get(id);
		return ConvertorUtil.convert(pojo);
	}

	public List<ChannelData> getAll() {
		List<ChannelPojo> list = channelService.getAll();
		return ConvertorUtil.convertChannels(list);
	}
	
	public ChannelData getByName(String name) throws ApiException {
		ChannelPojo pojo = channelService.getByName(name);
		return ConvertorUtil.convert(pojo);
	}
	
	public ChannelData update(Long id, ChannelForm form) throws ApiException {
		ChannelPojo pojo = ConvertorUtil.convert(form);
		ChannelPojo client = channelService.update(id,pojo);
		return ConvertorUtil.convert(client);
	}	
}

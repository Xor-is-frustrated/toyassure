package com.increff.channel.dto;

import com.increff.commons.data.ChannelData;
import com.increff.commons.form.ChannelForm;
import com.increff.channel.pojo.ChannelPojo;
import com.increff.channel.service.ApiException;
import com.increff.channel.service.ChannelService;
import com.increff.channel.util.ConvertorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

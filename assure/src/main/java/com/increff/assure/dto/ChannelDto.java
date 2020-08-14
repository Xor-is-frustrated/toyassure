package com.increff.assure.dto;

import com.increff.assure.pojo.ChannelPojo;
import com.increff.assure.service.ApiException;
import com.increff.assure.service.ChannelService;
import com.increff.assure.util.ConvertorUtil;
import com.increff.commons.data.ChannelData;
import com.increff.commons.data.PartyData;
import com.increff.commons.enums.InvoiceType;
import com.increff.commons.form.ChannelForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
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
        ChannelPojo client = channelService.update(id, pojo);
        return ConvertorUtil.convert(client);
    }

    @PostConstruct
    public void init() {
        ChannelForm form = new ChannelForm();
        form.setType(InvoiceType.SELF);
        form.setName("INTERNAL");
        ChannelPojo pojo = ConvertorUtil.convert(form);
        try {
            ChannelPojo channel = channelService.add(pojo);
        } catch (ApiException e) {
        }
    }

    public List<ChannelData> getAllSelf() {
        InvoiceType type= InvoiceType.SELF;
        return ConvertorUtil.convertChannels(channelService.getType(type));

    }

    public List<ChannelData> getAllCustomers() {
        InvoiceType type= InvoiceType.CHANNEL;
        return ConvertorUtil.convertChannels(channelService.getType(type));
    }
}

package com.increff.channel.service;

import com.increff.channel.dao.ChannelListingDao;
import com.increff.channel.pojo.ChannelListingPojo;
import com.increff.commons.data.ChannelData;
import com.increff.commons.data.PartyData;
import com.increff.commons.data.ProductData;

import com.increff.commons.enums.PartyType;
import com.increff.commons.form.ChannelListingForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ChannelListingService extends AbstractService {

    @Autowired
    private ChannelListingDao channelListingDao;

    @Transactional(rollbackFor = ApiException.class)
    public ChannelListingPojo add(ChannelListingForm form, PartyData client, ProductData product,
                                  ChannelData channel) throws ApiException {

        checkZero(form.getChannelSkuId().length(), "ChannelSkuId cannot be empty.");
        checkNotNull(channel, "Channel Id does not exist: " + form.getChannelId());
        checkNotNull(client, "Client does not exist: " + form.getClientName());
        checkNotNull(product, "Client Name and Client Sku combination does not exists. Client: " + form.getClientName() +
                ", Client Sku: " + form.getClientSkuId());
        System.out.println("10");
        if (client.getType() != PartyType.CLIENT) {
            throw new ApiException("Not a client: " + client.getName());
        }
        ChannelListingPojo cl = channelListingDao.selectByChannelSkuIdAndChannelId(form.getChannelSkuId(),
                channel.getId());
//        System.out.println("10"+cl.getChannelId());
        checkNull(cl, "Channel SKu and Channel Id combination already exists. Channel Sku: " + form.getChannelSkuId() + ", Channel: " + form.getChannelName());

        ChannelListingPojo pojo = new ChannelListingPojo();
        pojo.setChannelId(channel.getId());
        pojo.setChannelSkuId(form.getChannelSkuId());
        pojo.setClientId(client.getId());
        pojo.setGlobalSkuId(product.getId());
//        System.out.println("10"+cl.getChannelId());
        return channelListingDao.insert(pojo);
    }

    @Transactional(readOnly = true)
    public ChannelListingPojo get(Long id) throws ApiException {
        ChannelListingPojo p = channelListingDao.select(id);
        checkNotNull(p, "Channel Listing ID does not exist: " + id);
        return p;
    }

    @Transactional(readOnly = true)
    public ChannelListingPojo getByChannelSkuIdAndChannelId(String channelSkuId, ChannelData channel, ChannelListingForm form)throws ApiException {
        checkZero(channelSkuId.length(), "ChannelSkuId cannot be empty.");
        checkNotNull(channel, "Channel does not exist: "+form.getChannelName());
        ChannelListingPojo cl = channelListingDao.selectByChannelSkuIdAndChannelId(channelSkuId, channel.getId());
        checkNotNull(cl,"channelSkuId and channel name pair doesn't exist. Channel Sku: "+channelSkuId+", Channel: "+channel.getName());
        return cl;
    }

    @Transactional(readOnly = true)
    public List<ChannelListingPojo> getAll() {
        return channelListingDao.selectAll();
    }

    public List<ChannelListingPojo> getByChannelName(Long id) {
        return channelListingDao.selectByChannelId(id);
    }
}

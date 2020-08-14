package com.increff.channel.util;

import com.increff.channel.pojo.ChannelListingPojo;
import com.increff.commons.data.ChannelData;
import com.increff.commons.data.ChannelListingData;
import com.increff.commons.data.PartyData;
import com.increff.commons.data.ProductData;
import com.increff.commons.form.ChannelListingForm;

import java.util.ArrayList;
import java.util.List;

public class ConvertorUtil {

    public static ChannelListingPojo convert(ChannelListingForm form, PartyData client, ProductData product, ChannelData channel) {
        ChannelListingPojo cl = new ChannelListingPojo();
        cl.setChannelId(channel.getId());
        cl.setChannelSkuId(form.getChannelSkuId());
        cl.setGlobalSkuId(product.getId());
        cl.setClientId(client.getId());
        return cl;
    }

    public static ChannelListingData convert(ChannelListingPojo cl, ProductData product, String channelName) {
        ChannelListingData data = new ChannelListingData();
        data.setGlobalSkuId(cl.getGlobalSkuId());
        data.setProductName(product.getName());
        data.setClientSkuId(product.getClientSkuId());
        data.setClientName(product.getClientName());
        data.setChannelName(channelName);
        data.setChannelSkuId(cl.getChannelSkuId());
        data.setId(cl.getId());
        return data;
    }

    public static List<ChannelListingData> convertChannelListings(List<ChannelListingPojo> pojos, List<ProductData> product, List<String> channelNames) {
        List<ChannelListingData> list = new ArrayList<ChannelListingData>();
        for (int i = 0; i < pojos.size(); i++) {
            ChannelListingPojo data = pojos.get(i);
            list.add(convert(data, product.get(i), channelNames.get(i)));
        }
        return list;
    }
}

package com.increff.channel.util;


import com.increff.commons.data.ChannelData;
import com.increff.commons.data.ChannelListingData;
import com.increff.commons.data.ClientData;
import com.increff.commons.data.ProductData;
import com.increff.commons.form.ChannelForm;
import com.increff.commons.form.ChannelListingForm;
import com.increff.channel.pojo.ChannelListingPojo;
import com.increff.channel.pojo.ChannelPojo;


import java.util.ArrayList;
import java.util.List;

public class ConvertorUtil {



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

    public static ChannelListingPojo convert(ChannelListingForm form, ClientData client, ProductData product, ChannelPojo channel) {
        ChannelListingPojo cl= new ChannelListingPojo();

        cl.setChannel(channel);
        cl.setChannelSkuId(form.getChannelSkuId());
        cl.setGlobalSkuId(product.getId());
        cl.setClientId(client.getId());
        return cl;
    }

    public static ChannelListingData convert(ChannelListingPojo cl,ProductData product) {
        ChannelListingData data= new ChannelListingData();
        data.setGlobalSkuId(cl.getGlobalSkuId());
        data.setProductName(product.getName());
        data.setClientSkuId(product.getClientSkuId());
        data.setClientName(product.getClientName());
        data.setChannelName(cl.getChannel().getName());
        data.setChannelSkuId(cl.getChannelSkuId());
        data.setId(cl.getId());
        return data;
    }

    public static List<ChannelListingData> convertChannelListings(List<ChannelListingPojo> pojos,List<ProductData>product) {
        List<ChannelListingData> list= new ArrayList<ChannelListingData>();
        for(int i=0;i<pojos.size();i++) {
            ChannelListingPojo data = pojos.get(i);
            list.add(convert(data,product.get(i)));
        }
        return list;
    }
}

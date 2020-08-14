package com.increff.channel.util;

import com.increff.channel.pojo.ChannelListingPojo;
import com.increff.channel.service.AbstractUnitTest;
import com.increff.commons.data.ChannelData;
import com.increff.commons.data.ChannelListingData;
import com.increff.commons.data.PartyData;
import com.increff.commons.data.ProductData;
import com.increff.commons.enums.InvoiceType;
import com.increff.commons.enums.PartyType;
import com.increff.commons.form.ChannelListingForm;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConvertorUtilTest extends AbstractUnitTest {

    private PartyData partyData;
    private ProductData productData;
    private ChannelData channelData;
    private ChannelListingForm form;

    @Test
    public void testConvertData() {
        partyData = new PartyData();
        partyData.setId(1L);
        partyData.setType(PartyType.CLIENT);
        partyData.setName("client1");
        productData = new ProductData();
        productData.setId(1L);
        productData.setClientSkuId("clientsku1");
        productData.setClientName("client1");
        productData.setName("product1");
        productData.setDescription("des about product");
        productData.setBrandId("brandId");
        productData.setMrp(10.1);
        channelData = new ChannelData();
        channelData.setName("amazon");
        channelData.setId(1L);
        channelData.setType(InvoiceType.CHANNEL);
        form = new ChannelListingForm();
        form.setChannelId(1L);
        form.setChannelName("amazon");
        form.setClientSkuId("clientsku1");
        form.setChannelSkuId("channelsku1");
        form.setClientName("client1");
        ChannelListingPojo pojo = ConvertorUtil.convert(form, partyData, productData, channelData);
        assertEquals(form.getChannelSkuId(), pojo.getChannelSkuId());
        assertEquals(channelData.getId(), pojo.getChannelId());
        assertEquals(partyData.getId(), pojo.getClientId());

    }

    @Test
    public void testConvertPojo() {
        partyData = new PartyData();
        partyData.setId(1L);
        partyData.setType(PartyType.CLIENT);
        partyData.setName("client1");
        productData = new ProductData();
        productData.setId(1L);
        productData.setClientSkuId("clientsku1");
        productData.setClientName("client1");
        productData.setName("product1");
        productData.setDescription("des about product");
        productData.setBrandId("brandId");
        productData.setMrp(10.1);
        channelData = new ChannelData();
        channelData.setName("amazon");
        channelData.setId(1L);
        channelData.setType(InvoiceType.CHANNEL);
        form = new ChannelListingForm();
        form.setChannelId(1L);
        form.setChannelName("amazon");
        form.setClientSkuId("clientsku1");
        form.setChannelSkuId("channelsku1");
        form.setClientName("client1");
        ChannelListingPojo pojo = new ChannelListingPojo();
        pojo.setId(1L);
        pojo.setChannelId(1L);
        pojo.setClientId(1L);
        pojo.setGlobalSkuId(1L);
        pojo.setChannelSkuId("channelsku1");
        ChannelListingData data = ConvertorUtil.convert(pojo, productData, "amazon");
        assertEquals(pojo.getChannelSkuId(), data.getChannelSkuId());
        assertEquals(pojo.getId(), data.getId());
        assertEquals(pojo.getGlobalSkuId(), data.getGlobalSkuId());

    }

}

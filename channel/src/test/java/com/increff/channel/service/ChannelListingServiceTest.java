package com.increff.channel.service;

import com.increff.channel.pojo.ChannelListingPojo;
import com.increff.commons.data.ChannelData;
import com.increff.commons.data.PartyData;
import com.increff.commons.data.ProductData;
import com.increff.commons.enums.InvoiceType;
import com.increff.commons.enums.PartyType;
import com.increff.commons.form.ChannelListingForm;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;


public class ChannelListingServiceTest extends AbstractUnitTest {

    @Autowired
    private ChannelListingService channelListingService;

    @Test
    public void testAdd() throws ApiException {
        PartyData party = new PartyData();
        party.setName("client");
        party.setType(PartyType.CLIENT);
        party.setId(1L);

        ProductData c = new ProductData();
        c.setId(1L);
        c.setName("assure");
        c.setBrandId("brand");
        c.setClientSkuId("clientsku1");
        c.setDescription("this is description");
        c.setMrp(1.1);
        c.setClientName("assure");

        ChannelData channel = new ChannelData();
        channel.setName("channel");
        channel.setType(InvoiceType.CHANNEL);
        channel.setId(1L);

        ChannelListingForm cl = new ChannelListingForm();
        cl.setClientName("channel");
        cl.setClientName("client");
        cl.setChannelSkuId("channelsku");

        channelListingService.add(cl, party, c, channel);
    }


    @Test
    public void testSelect() throws ApiException {
        PartyData party = new PartyData();
        party.setName("client");
        party.setType(PartyType.CLIENT);
        party.setId(1L);

        ProductData c = new ProductData();
        c.setId(1L);
        c.setName("assure");
        c.setBrandId("brand");
        c.setClientSkuId("clientsku1");
        c.setDescription("this is description");
        c.setMrp(1.1);
        c.setClientName("assure");

        ChannelData channel = new ChannelData();
        channel.setName("channel");
        channel.setType(InvoiceType.CHANNEL);
        channel.setId(1L);

        ChannelListingForm cl = new ChannelListingForm();
        cl.setClientName("channel");
        cl.setClientName("client");
        cl.setChannelSkuId("channelsku");

        ChannelListingPojo pojo = channelListingService.add(cl, party, c, channel);

        ChannelListingPojo list = channelListingService.get(pojo.getId());
        assertEquals(pojo.getClientId(), list.getClientId());
        assertEquals(pojo.getChannelId(), list.getChannelId());
        assertEquals(pojo.getChannelSkuId(), list.getChannelSkuId());
        assertEquals(pojo.getGlobalSkuId(), list.getGlobalSkuId());

    }

    @Test
    public void testSelectAll() throws ApiException {
        PartyData party = new PartyData();
        party.setName("client");
        party.setType(PartyType.CLIENT);
        party.setId(1L);

        ProductData c = new ProductData();
        c.setId(1L);
        c.setName("assure");
        c.setBrandId("brand");
        c.setClientSkuId("clientsku1");
        c.setDescription("this is description");
        c.setMrp(1.1);
        c.setClientName("assure");

        ChannelData channel = new ChannelData();
        channel.setName("channel");
        channel.setType(InvoiceType.CHANNEL);
        channel.setId(1L);

        ChannelListingForm cl = new ChannelListingForm();
        cl.setClientName("channel");
        cl.setClientName("client");
        cl.setChannelSkuId("channelsku");

        ChannelListingPojo pojo = channelListingService.add(cl, party, c, channel);

        List<ChannelListingPojo> list = channelListingService.getAll();
        assertEquals(pojo.getClientId(), list.get(0).getClientId());
        assertEquals(pojo.getChannelId(), list.get(0).getChannelId());
        assertEquals(pojo.getChannelSkuId(), list.get(0).getChannelSkuId());
        assertEquals(pojo.getGlobalSkuId(), list.get(0).getGlobalSkuId());
    }

    @Test
    public void testSelectByParams() throws ApiException {
        PartyData party = new PartyData();
        party.setName("client");
        party.setType(PartyType.CLIENT);
        party.setId(1L);

        ProductData c = new ProductData();
        c.setId(1L);
        c.setName("assure");
        c.setBrandId("brand");
        c.setClientSkuId("clientsku1");
        c.setDescription("this is description");
        c.setMrp(1.1);
        c.setClientName("assure");

        ChannelData channel = new ChannelData();
        channel.setName("channel");
        channel.setType(InvoiceType.CHANNEL);
        channel.setId(1L);

        ChannelListingForm cl = new ChannelListingForm();
        cl.setClientName("channel");
        cl.setClientName("client");
        cl.setChannelSkuId("channelsku");

        ChannelListingPojo pojo = channelListingService.add(cl, party, c, channel);

        ChannelListingPojo list = channelListingService.getByChannelSkuIdAndChannelId("channelsku", channel, cl);
        assertEquals(pojo.getClientId(), list.getClientId());
        assertEquals(pojo.getChannelId(), list.getChannelId());
        assertEquals(pojo.getChannelSkuId(), list.getChannelSkuId());
        assertEquals(pojo.getGlobalSkuId(), list.getGlobalSkuId());

    }

}

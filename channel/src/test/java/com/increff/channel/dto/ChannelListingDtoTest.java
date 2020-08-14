package com.increff.channel.dto;

import com.increff.channel.client.ClientAssure;
import com.increff.channel.service.AbstractUnitTest;
import com.increff.channel.service.ApiException;
import com.increff.commons.data.ChannelData;
import com.increff.commons.data.ChannelListingData;
import com.increff.commons.data.PartyData;
import com.increff.commons.data.ProductData;
import com.increff.commons.enums.InvoiceType;
import com.increff.commons.enums.PartyType;
import com.increff.commons.form.ChannelForm;
import com.increff.commons.form.ChannelListingForm;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class ChannelListingDtoTest extends AbstractUnitTest {

    private ChannelListingForm channelListingForm1, channelListingForm2;
    private ProductData productData1, productData2;
    private PartyData PartyData;
    private ChannelForm channelForm1;


    @Autowired
    private ChannelListingDto channelListingDto;

    @Mock
    private ClientAssure clientAssure;

    private PartyData partyData;
    private ProductData productData;
    private ChannelData channelData;
    private ChannelListingForm form;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
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

    }


    @Test
    public void testAdd() throws ApiException {
        channelListingDto.setChannelRestTemplate(clientAssure);
        when(clientAssure.getByName("amazon")).thenReturn(channelData);
        when(clientAssure.getClientByName("client1")).thenReturn(partyData);
        when(clientAssure.getProductByClientNameAndClientSkuId("clientsku1", "client1")).thenReturn(productData);
        channelListingDto.add(form);

    }

    @Test
    public void testGet() throws ApiException{
		channelListingDto.setChannelRestTemplate(clientAssure);
        when(clientAssure.getByName("amazon")).thenReturn(channelData);
        when(clientAssure.getClientByName("client1")).thenReturn(partyData);
        when(clientAssure.getProductByClientNameAndClientSkuId("clientsku1", "client1")).thenReturn(productData);
		when(clientAssure.getProduct(1L)).thenReturn(productData);
		when(clientAssure.selectChannel(1L)).thenReturn(channelData);
		ChannelListingData data=channelListingDto.add(form);

		ChannelListingData list= channelListingDto.get(data.getId());
        assertEquals(data.getChannelSkuId(), list.getChannelSkuId());
		assertEquals(data.getChannelName(), list.getChannelName());
		assertEquals(data.getClientName(), list.getClientName());
		assertEquals(data.getClientSkuId(), list.getClientSkuId());

	}

    @Test
    public void testGetByParams() throws ApiException{
        channelListingDto.setChannelRestTemplate(clientAssure);
        when(clientAssure.getByName("amazon")).thenReturn(channelData);
        when(clientAssure.getClientByName("client1")).thenReturn(partyData);
        when(clientAssure.getProductByClientNameAndClientSkuId("clientsku1", "client1")).thenReturn(productData);
        when(clientAssure.getProduct(1L)).thenReturn(productData);
        when(clientAssure.selectChannel(1L)).thenReturn(channelData);
        ChannelListingData data=channelListingDto.add(form);

        ChannelListingData list= channelListingDto.getByChannelIdAndSku(form);
        assertEquals(data.getChannelSkuId(), list.getChannelSkuId());
        assertEquals(data.getChannelName(), list.getChannelName());
        assertEquals(data.getClientName(), list.getClientName());
        assertEquals(data.getClientSkuId(), list.getClientSkuId());

    }

    @Test
    public void testGetAll() throws ApiException{
        channelListingDto.setChannelRestTemplate(clientAssure);
        when(clientAssure.getByName("amazon")).thenReturn(channelData);
        when(clientAssure.getClientByName("client1")).thenReturn(partyData);
        when(clientAssure.getProductByClientNameAndClientSkuId("clientsku1", "client1")).thenReturn(productData);
        when(clientAssure.getProduct(1L)).thenReturn(productData);
        when(clientAssure.selectChannel(1L)).thenReturn(channelData);
        ChannelListingData data=channelListingDto.add(form);

        List<ChannelListingData> list= channelListingDto.getAll();
        assertEquals(data.getChannelSkuId(), list.get(0).getChannelSkuId());
        assertEquals(data.getChannelName(), list.get(0).getChannelName());
        assertEquals(data.getClientName(), list.get(0).getClientName());
        assertEquals(data.getClientSkuId(), list.get(0).getClientSkuId());

    }

}

package com.increff.channel.dto;

import com.increff.channel.assure.Client;
import com.increff.channel.assure.Product;
import com.increff.channel.pojo.ChannelListingPojo;
import com.increff.channel.pojo.ChannelPojo;
import com.increff.channel.service.AbstractUnitTest;
import com.increff.channel.service.ChannelListingService;
import com.increff.channel.service.ChannelService;
import com.increff.commons.data.ChannelData;
import com.increff.commons.data.ChannelListingData;
import com.increff.commons.data.ClientData;
import com.increff.commons.data.ProductData;
import com.increff.commons.enums.ChannelType;
import com.increff.commons.enums.ClientType;
import com.increff.commons.form.ChannelForm;
import com.increff.commons.form.ChannelListingForm;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ChannelListingDtoTest extends AbstractUnitTest {

	private ChannelListingForm channelListingForm1, channelListingForm2;
	private ProductData productData1, productData2;
	private ClientData clientData;
	private ChannelForm channelForm1;


	@Autowired
	private ChannelListingDto channelListingDto;

	@Autowired
	private ChannelDto channelDto;

	@Mock
	private Client client;

	@Mock
	private Product product;

	@Test
	public void test(){

	}


	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		channelForm1 = createChannelForm();
		channelListingForm1 = createChannelListingForm("prod1", "channelProd1");
		channelListingForm2 = createChannelListingForm("prod2", "channelProd2");
		clientData = createClientData();
		productData1 = createProductData("prod1", 1L, "munch", 10.50);
		productData2 = createProductData("prod2", 2L, "kitkat", 15);
		
	}

	private ProductData createProductData(String clientskuid, long globalskuid, String name, double mrp) {
		ProductData data= new ProductData();

		data.setBrandId("brandId");
		data.setClientName(clientData.getName());
		data.setDescription("description");
		data.setName(name);
		data.setClientSkuId(clientskuid);
		data.setId(globalskuid);
		return data;
	}

	private ClientData createClientData() {
		ClientData data= new ClientData();
		data.setId(1L);
		data.setName("ganesh");
		data.setType(ClientType.CLIENT);
		return data;
	}

	private ChannelListingForm createChannelListingForm(String clientskuid, String channelskuid) {
		ChannelListingForm form= new ChannelListingForm();
		form.setClientName("ganesh");
		form.setChannelSkuId(channelskuid);
		form.setClientSkuId(clientskuid);
		form.setChannelId(1L);
		return form;
	}

	private ChannelForm createChannelForm() {
		ChannelForm form = new ChannelForm();
		form.setName("amazon");
		form.setType(ChannelType.CHANNEL);
		return form;
	}

//	private void addDetails() {
//		ChannelData channelData = channelDto.add(channelForm1);
//		List<ChannelListingForm> channelListingFormList = new ArrayList<>();
//		channelListingForm1.setChannelId(channelData.getId());
//		channelListingForm2.setChannelId(channelData.getId());
//
//		channelListingFormList.add(channelListingForm1);
//		channelListingFormList.add(channelListingForm2);
//		channelListingCsvForm.setChannelListingFormList(channelListingFormList);
//		channelListingDto.setClientAssureRestTemplate(clientAssure);
//		channelListingDto.setProductAssureRestTemplate(productAssure);
//		List<ProductData> productDataList = new ArrayList<>();
//		productDataList.add(productData1);
//		productDataList.add(productData2);
//		when(clientAssure.getClientData(1L)).thenReturn(clientData);
//		when(productAssure.getProductByClientIdAndClientSkuId(1L)).thenReturn(productDataList);
//		when(productAssure.getProductData(1L)).thenReturn(productData1);
//		when(productAssure.getProductData(2L)).thenReturn(productData2);
//	}
//
//	@Test(expected = Exception.class)
//	public void testAddChannelListing() {
//		addDetails();
//		BindingResult result = mock(BindingResult.class);
//		when(result.hasErrors()).thenReturn(false);
//		channelListingDto.addChannelListing(channelListingCsvForm, result);
//		when(result.hasErrors()).thenReturn(true);
//		channelListingDto.addChannelListing(channelListingCsvForm, result);
//	}


//	@Autowired
//	private ClientService clientService;
//
//	@Autowired
//	private ProductService productService;
//
//	@Autowired
//	private ChannelService channelService;
//
//	@Autowired
//	private ChannelListingDto channelListingDto;
//
//	@Autowired
//	private ChannelListingService channelListingService;
//
//	@Test
//	public void testAdd() throws ApiException {
//		ClientPojo client = new ClientPojo();
//		client.setName("assure");
//		client.setType(ClientType.CLIENT);
//
//		clientService.add(client);
//
//		ProductPojo c = new ProductPojo();
//		c.setName("assure");
//		c.setBrandId("brand");
//		c.setClientSkuId("clientsku");
//		c.setDescription("this is description");
//		c.setMrp(1.1);
//		c.setClient(client);
//
//		productService.add(c);
//
//		ChannelPojo channel = new ChannelPojo();
//		channel.setName("channel");
//		channel.setType(ChannelType.CHANNEL);
//
//		channelService.add(channel);
//
//		ChannelListingForm form = new ChannelListingForm();
//		form.setChannelName("channel");
//		form.setChannelSkuId("channelsku");
//		form.setClientName("assure");
//		form.setClientSkuId("clientsku");
//
//		channelListingDto.add(form);
//
//	}
//
//	@Test
//	public void testSelect() throws ApiException {
//		ClientPojo client = new ClientPojo();
//		client.setName("assure");
//		client.setType(ClientType.CLIENT);
//
//		clientService.add(client);
//
//		ProductPojo c = new ProductPojo();
//		c.setName("assure");
//		c.setBrandId("brand");
//		c.setClientSkuId("clientsku");
//		c.setDescription("this is description");
//		c.setMrp(1.1);
//		c.setClient(client);
//
//		productService.add(c);
//
//		ChannelPojo channel = new ChannelPojo();
//		channel.setName("channel");
//		channel.setType(ChannelType.CHANNEL);
//
//		channelService.add(channel);
//
//		ChannelListingPojo cl = new ChannelListingPojo();
//		cl.setChannel(channel);
//		cl.setClient(client);
//		cl.setProduct(c);
//		cl.setChannelSkuId("channelsku");
//
//		channelListingService.add(cl);
//
//		ChannelListingData list = channelListingDto.get(cl.getId());
//		assertEquals(cl.getChannelSkuId(), list.getChannelSkuId());
//		assertEquals(cl.getChannel().getName(), list.getChannelName());
//		assertEquals(cl.getClient().getName(), list.getClientName());
//		assertEquals(cl.getProduct().getClientSkuId(), list.getClientSkuId());
//
//	}
//
//	@Test
//	public void testSelectAll() throws ApiException {
//		ClientPojo client = new ClientPojo();
//		client.setName("assure");
//		client.setType(ClientType.CLIENT);
//
//		clientService.add(client);
//
//		ProductPojo c = new ProductPojo();
//		c.setName("assure");
//		c.setBrandId("brand");
//		c.setClientSkuId("clientsku");
//		c.setDescription("this is description");
//		c.setMrp(1.1);
//		c.setClient(client);
//
//		productService.add(c);
//
//		ChannelPojo channel = new ChannelPojo();
//		channel.setName("channel");
//		channel.setType(ChannelType.CHANNEL);
//
//		channelService.add(channel);
//
//		ChannelListingPojo cl = new ChannelListingPojo();
//		cl.setChannel(channel);
//		cl.setClient(client);
//		cl.setProduct(c);
//		cl.setChannelSkuId("channelsku");
//
//		channelListingService.add(cl);
//
//		ChannelListingPojo cl1 = new ChannelListingPojo();
//		cl1.setChannel(channel);
//		cl1.setClient(client);
//		cl1.setProduct(c);
//		cl1.setChannelSkuId("channelsku1");
//
//		channelListingService.add(cl1);
//
//		List<ChannelListingData> list = channelListingDto.getAll();
//		assertEquals(2, list.size());
//		assertEquals(cl.getChannelSkuId(), list.get(0).getChannelSkuId());
//		assertEquals(cl.getChannel().getName(), list.get(0).getChannelName());
//		assertEquals(cl.getClient().getName(), list.get(0).getClientName());
//		assertEquals(cl.getProduct().getClientSkuId(), list.get(0).getClientSkuId());
//
//		assertEquals(cl1.getChannelSkuId(), list.get(1).getChannelSkuId());
//		assertEquals(cl1.getChannel().getName(), list.get(1).getChannelName());
//		assertEquals(cl1.getClient().getName(), list.get(1).getClientName());
//		assertEquals(cl1.getProduct().getClientSkuId(), list.get(1).getClientSkuId());
//	}
//
}

package com.increff.assure.dto;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.increff.assure.model.ChannelListingData;
import com.increff.assure.model.ChannelListingForm;
import com.increff.assure.pojo.ChannelListingPojo;
import com.increff.assure.pojo.ChannelPojo;
import com.increff.assure.pojo.ChannelType;
import com.increff.assure.pojo.ClientPojo;
import com.increff.assure.pojo.ClientType;
import com.increff.assure.pojo.ProductPojo;
import com.increff.assure.service.AbstractUnitTest;
import com.increff.assure.service.ApiException;
import com.increff.assure.service.ChannelListingService;
import com.increff.assure.service.ChannelService;
import com.increff.assure.service.ClientService;
import com.increff.assure.service.ProductService;

public class ChannelListingDtoTest extends AbstractUnitTest {
	@Autowired
	private ClientService clientService;

	@Autowired
	private ProductService productService;

	@Autowired
	private ChannelService channelService;

	@Autowired
	private ChannelListingDto channelListingDto;

	@Autowired
	private ChannelListingService channelListingService;

	@Test
	public void testAdd() throws ApiException {
		ClientPojo client = new ClientPojo();
		client.setName("assure");
		client.setType(ClientType.CLIENT);

		clientService.add(client);

		ProductPojo c = new ProductPojo();
		c.setName("assure");
		c.setBrandId("brand");
		c.setClientSkuId("clientsku");
		c.setDescription("this is description");
		c.setMrp(1.1);
		c.setClient(client);

		productService.add(c);

		ChannelPojo channel = new ChannelPojo();
		channel.setName("channel");
		channel.setType(ChannelType.CHANNEL);

		channelService.add(channel);

		ChannelListingForm form = new ChannelListingForm();
		form.setChannelName("channel");
		form.setChannelSkuId("channelsku");
		form.setClientName("assure");
		form.setClientSkuId("clientsku");

		channelListingDto.add(form);

	}

	@Test
	public void testSelect() throws ApiException {
		ClientPojo client = new ClientPojo();
		client.setName("assure");
		client.setType(ClientType.CLIENT);

		clientService.add(client);

		ProductPojo c = new ProductPojo();
		c.setName("assure");
		c.setBrandId("brand");
		c.setClientSkuId("clientsku");
		c.setDescription("this is description");
		c.setMrp(1.1);
		c.setClient(client);

		productService.add(c);

		ChannelPojo channel = new ChannelPojo();
		channel.setName("channel");
		channel.setType(ChannelType.CHANNEL);

		channelService.add(channel);

		ChannelListingPojo cl = new ChannelListingPojo();
		cl.setChannel(channel);
		cl.setClient(client);
		cl.setProduct(c);
		cl.setChannelSkuId("channelsku");

		channelListingService.add(cl);

		ChannelListingData list = channelListingDto.get(cl.getId());
		assertEquals(cl.getChannelSkuId(), list.getChannelSkuId());
		assertEquals(cl.getChannel().getName(), list.getChannelName());
		assertEquals(cl.getClient().getName(), list.getClientName());
		assertEquals(cl.getProduct().getClientSkuId(), list.getClientSkuId());

	}

	@Test
	public void testSelectAll() throws ApiException {
		ClientPojo client = new ClientPojo();
		client.setName("assure");
		client.setType(ClientType.CLIENT);

		clientService.add(client);

		ProductPojo c = new ProductPojo();
		c.setName("assure");
		c.setBrandId("brand");
		c.setClientSkuId("clientsku");
		c.setDescription("this is description");
		c.setMrp(1.1);
		c.setClient(client);

		productService.add(c);

		ChannelPojo channel = new ChannelPojo();
		channel.setName("channel");
		channel.setType(ChannelType.CHANNEL);

		channelService.add(channel);

		ChannelListingPojo cl = new ChannelListingPojo();
		cl.setChannel(channel);
		cl.setClient(client);
		cl.setProduct(c);
		cl.setChannelSkuId("channelsku");

		channelListingService.add(cl);

		ChannelListingPojo cl1 = new ChannelListingPojo();
		cl1.setChannel(channel);
		cl1.setClient(client);
		cl1.setProduct(c);
		cl1.setChannelSkuId("channelsku1");

		channelListingService.add(cl1);

		List<ChannelListingData> list = channelListingDto.getAll();
		assertEquals(2, list.size());
		assertEquals(cl.getChannelSkuId(), list.get(0).getChannelSkuId());
		assertEquals(cl.getChannel().getName(), list.get(0).getChannelName());
		assertEquals(cl.getClient().getName(), list.get(0).getClientName());
		assertEquals(cl.getProduct().getClientSkuId(), list.get(0).getClientSkuId());

		assertEquals(cl1.getChannelSkuId(), list.get(1).getChannelSkuId());
		assertEquals(cl1.getChannel().getName(), list.get(1).getChannelName());
		assertEquals(cl1.getClient().getName(), list.get(1).getClientName());
		assertEquals(cl1.getProduct().getClientSkuId(), list.get(1).getClientSkuId());
	}

}

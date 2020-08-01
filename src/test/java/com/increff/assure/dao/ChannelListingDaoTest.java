package com.increff.assure.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.increff.assure.pojo.ChannelListingPojo;
import com.increff.assure.pojo.ChannelPojo;
import com.increff.assure.pojo.ChannelType;
import com.increff.assure.pojo.ClientPojo;
import com.increff.assure.pojo.ClientType;
import com.increff.assure.pojo.ProductPojo;
import com.increff.assure.service.AbstractUnitTest;

public class ChannelListingDaoTest extends AbstractUnitTest {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private ClientDao clientDao;

	@Autowired
	private ChannelDao channelDao;

	@Autowired
	private ChannelListingDao channelListingDao;

	@Test
	public void testInsert() {

		ClientPojo client = new ClientPojo();
		client.setName("assure");
		client.setType(ClientType.CLIENT);

		clientDao.insert(client);

		ProductPojo c = new ProductPojo();
		c.setName("assure");
		c.setBrandId("brand");
		c.setClientSkuId("clientsku");
		c.setDescription("this is description");
		c.setMrp(1.1);
		c.setClient(client);

		productDao.insert(c);

		ChannelPojo channel = new ChannelPojo();
		channel.setName("channel");
		channel.setType(ChannelType.CHANNEL);

		channelDao.insert(channel);

		ChannelListingPojo cl = new ChannelListingPojo();
		cl.setChannel(channel);
		cl.setClient(client);
		cl.setProduct(c);
		cl.setChannelSkuId("channelsku");

		channelListingDao.insert(cl);

	}

	@Test
	public void testSelect() {
		ClientPojo client = new ClientPojo();
		client.setName("assure");
		client.setType(ClientType.CLIENT);

		clientDao.insert(client);

		ProductPojo c = new ProductPojo();
		c.setName("assure");
		c.setBrandId("brand");
		c.setClientSkuId("clientsku");
		c.setDescription("this is description");
		c.setMrp(1.1);
		c.setClient(client);

		productDao.insert(c);

		ChannelPojo channel = new ChannelPojo();
		channel.setName("channel");
		channel.setType(ChannelType.CHANNEL);

		channelDao.insert(channel);

		ChannelListingPojo cl = new ChannelListingPojo();
		cl.setChannel(channel);
		cl.setClient(client);
		cl.setProduct(c);
		cl.setChannelSkuId("channelsku");

		channelListingDao.insert(cl);

		ChannelListingPojo cl1 = new ChannelListingPojo();
		cl1.setChannel(channel);
		cl1.setClient(client);
		cl1.setProduct(c);
		cl1.setChannelSkuId("channelsku1");

		channelListingDao.insert(cl1);

		ChannelListingPojo list = channelListingDao.select(cl.getId());
		assertEquals(cl.getChannelSkuId(), list.getChannelSkuId());
		assertEquals(cl.getProduct(), list.getProduct());
		assertEquals(cl.getClient(), list.getClient());
		assertEquals(cl.getChannel(), list.getChannel());
	}

	@Test
	public void testSelectByParams() {
		ClientPojo client = new ClientPojo();
		client.setName("assure");
		client.setType(ClientType.CLIENT);

		clientDao.insert(client);

		ProductPojo c = new ProductPojo();
		c.setName("assure");
		c.setBrandId("brand");
		c.setClientSkuId("clientsku");
		c.setDescription("this is description");
		c.setMrp(1.1);
		c.setClient(client);

		productDao.insert(c);

		ChannelPojo channel = new ChannelPojo();
		channel.setName("channel");
		channel.setType(ChannelType.CHANNEL);

		channelDao.insert(channel);

		ChannelListingPojo cl = new ChannelListingPojo();
		cl.setChannel(channel);
		cl.setClient(client);
		cl.setProduct(c);
		cl.setChannelSkuId("channelsku");

		channelListingDao.insert(cl);

		ChannelListingPojo cl1 = new ChannelListingPojo();
		cl1.setChannel(channel);
		cl1.setClient(client);
		cl1.setProduct(c);
		cl1.setChannelSkuId("channelsku1");

		channelListingDao.insert(cl1);

		ChannelListingPojo list = channelListingDao.selectByChannelSkuIdAndChannel(
				"channelsku", channel);
		assertEquals(cl.getChannelSkuId(), list.getChannelSkuId());
		assertEquals(cl.getProduct(), list.getProduct());
		assertEquals(cl.getClient(), list.getClient());
		assertEquals(cl.getChannel(), list.getChannel());
	}

	@Test
	public void testSelectAll() {
		ClientPojo client = new ClientPojo();
		client.setName("assure");
		client.setType(ClientType.CLIENT);

		clientDao.insert(client);

		ProductPojo c = new ProductPojo();
		c.setName("assure");
		c.setBrandId("brand");
		c.setClientSkuId("clientsku");
		c.setDescription("this is description");
		c.setMrp(1.1);
		c.setClient(client);

		productDao.insert(c);

		ChannelPojo channel = new ChannelPojo();
		channel.setName("channel");
		channel.setType(ChannelType.CHANNEL);

		channelDao.insert(channel);

		ChannelListingPojo cl = new ChannelListingPojo();
		cl.setChannel(channel);
		cl.setClient(client);
		cl.setProduct(c);
		cl.setChannelSkuId("channelsku");

		channelListingDao.insert(cl);

		ChannelListingPojo cl1 = new ChannelListingPojo();
		cl1.setChannel(channel);
		cl1.setClient(client);
		cl1.setProduct(c);
		cl1.setChannelSkuId("channelsku1");

		channelListingDao.insert(cl1);

		List<ChannelListingPojo> list = channelListingDao.selectAll();
		assertEquals(2, list.size());
		assertEquals(cl.getChannelSkuId(), list.get(0).getChannelSkuId());
		assertEquals(cl.getProduct(), list.get(0).getProduct());
		assertEquals(cl.getClient(), list.get(0).getClient());
		assertEquals(cl.getChannel(), list.get(0).getChannel());

		assertEquals(cl1.getChannelSkuId(), list.get(1).getChannelSkuId());
		assertEquals(cl1.getProduct(), list.get(1).getProduct());
		assertEquals(cl1.getClient(), list.get(1).getClient());
		assertEquals(cl1.getChannel(), list.get(1).getChannel());
	}

}

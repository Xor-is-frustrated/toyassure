package com.increff.channel.dao;


import com.increff.channel.pojo.ChannelListingPojo;
import com.increff.channel.service.AbstractUnitTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ChannelListingDaoTest extends AbstractUnitTest {

	@Autowired
	private ChannelListingDao channelListingDao;

	@Test
	public void testInsert() {
		ChannelListingPojo cl = new ChannelListingPojo();
		cl.setGlobalSkuId(1L);
		cl.setClientId(2L);
		cl.setChannelId(3L);
		cl.setChannelSkuId("channelsku");

		channelListingDao.insert(cl);

	}

	@Test
	public void testSelect() {
		ChannelListingPojo cl = new ChannelListingPojo();
		cl.setGlobalSkuId(1L);
		cl.setClientId(2L);
		cl.setChannelId(3L);
		cl.setChannelSkuId("channelsku");

		ChannelListingPojo pojo=channelListingDao.insert(cl);

		ChannelListingPojo list = channelListingDao.select(pojo.getId());
		assertEquals(pojo.getChannelSkuId(), list.getChannelSkuId());
		assertEquals(pojo.getGlobalSkuId(), list.getGlobalSkuId());
		assertEquals(pojo.getClientId(), list.getClientId());
		assertEquals(pojo.getChannelId(), list.getChannelId());
	}

	@Test
	public void testSelectByParams() {
		ChannelListingPojo cl = new ChannelListingPojo();
		cl.setGlobalSkuId(1L);
		cl.setClientId(2L);
		cl.setChannelId(3L);
		cl.setChannelSkuId("channelsku");

		ChannelListingPojo pojo=channelListingDao.insert(cl);

		ChannelListingPojo list = channelListingDao.selectByChannelSkuIdAndChannelId("channelsku",3L);
		assertEquals(pojo.getChannelSkuId(), list.getChannelSkuId());
		assertEquals(pojo.getGlobalSkuId(), list.getGlobalSkuId());
		assertEquals(pojo.getClientId(), list.getClientId());
		assertEquals(pojo.getChannelId(), list.getChannelId());
	}

	@Test
	public void testSelectAll() {
		ChannelListingPojo cl = new ChannelListingPojo();
		cl.setGlobalSkuId(1L);
		cl.setClientId(2L);
		cl.setChannelId(3L);
		cl.setChannelSkuId("channelsku");

		ChannelListingPojo pojo=channelListingDao.insert(cl);

		List<ChannelListingPojo> list = channelListingDao.selectAll();
		assertEquals(pojo.getChannelSkuId(), list.get(0).getChannelSkuId());
		assertEquals(pojo.getGlobalSkuId(), list.get(0).getGlobalSkuId());
		assertEquals(pojo.getClientId(), list.get(0).getClientId());
		assertEquals(pojo.getChannelId(), list.get(0).getChannelId());
	}

}

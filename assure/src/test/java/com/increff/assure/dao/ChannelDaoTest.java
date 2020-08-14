package com.increff.assure.dao;

import com.increff.assure.pojo.ChannelPojo;
import com.increff.assure.service.AbstractUnitTest;
import com.increff.commons.enums.InvoiceType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ChannelDaoTest extends AbstractUnitTest {

	@Autowired
	private ChannelDao channelDao;

	@Test 
	public void testInsert() {
		ChannelPojo channel = new ChannelPojo();
		channel.setName("channel");
		channel.setType(InvoiceType.CHANNEL);

		channelDao.insert(channel);

	}

	@Test
	public void testSelect() {
		ChannelPojo channel = new ChannelPojo();
		channel.setName("channel");
		channel.setType(InvoiceType.CHANNEL);

		channelDao.insert(channel);
		ChannelPojo channel1 = new ChannelPojo();
		channel1.setName("channel1");
		channel1.setType(InvoiceType.CHANNEL);

		channelDao.insert(channel1);

		ChannelPojo list = channelDao.select(channel.getId());
		assertEquals(channel.getName(), list.getName());
		assertEquals(channel.getType(), list.getType());

	}

	@Test
	public void testSelectByName() {
		ChannelPojo channel = new ChannelPojo();
		channel.setName("channel");
		channel.setType(InvoiceType.CHANNEL);

		channelDao.insert(channel);
		ChannelPojo channel1 = new ChannelPojo();
		channel1.setName("channel1");
		channel1.setType(InvoiceType.CHANNEL);

		channelDao.insert(channel1);

		ChannelPojo list = channelDao.selectByName(channel.getName());
		assertEquals(channel.getName(), list.getName());
		assertEquals(channel.getType(), list.getType());

	}

	@Test
	public void testSelectAll() {
		ChannelPojo channel = new ChannelPojo();
		channel.setName("channel");
		channel.setType(InvoiceType.CHANNEL);

		channelDao.insert(channel);

		ChannelPojo channel1 = new ChannelPojo();
		channel1.setName("channel1");
		channel1.setType(InvoiceType.CHANNEL);

		channelDao.insert(channel1);

		List<ChannelPojo> list = channelDao.selectAll();

		assertEquals(3, list.size());
		assertEquals(channel.getName(), list.get(1).getName());
		assertEquals(channel.getType(), list.get(1).getType());

		assertEquals(channel1.getName(), list.get(2).getName());
		assertEquals(channel1.getType(), list.get(2).getType());

	}

}

package com.increff.assure.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.increff.assure.pojo.BinPojo;
import com.increff.assure.service.AbstractUnitTest;

public class BinDaoTest extends AbstractUnitTest {

	@Autowired
	private BinDao binDao;

	@Test
	public void testInsert() {
		BinPojo c = new BinPojo();
		binDao.insert(c);

	}

	@Test
	public void testSelectAll() {
		BinPojo c = new BinPojo();
		binDao.insert(c);

		BinPojo c1 = new BinPojo();
		binDao.insert(c1);

		List<BinPojo> list = binDao.selectAll();

		assertEquals(2, list.size());
		assertEquals(c.getBinId(), list.get(0).getBinId());
		assertEquals(c1.getBinId(), list.get(1).getBinId());

	}

	@Test
	public void testById() {
		BinPojo c = new BinPojo();
		binDao.insert(c);

		BinPojo c1 = new BinPojo();
		binDao.insert(c1);

		BinPojo list = binDao.select(c.getBinId());
		assertEquals(c.getBinId(), list.getBinId());
	}

}

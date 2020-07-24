package com.increff.assure.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.increff.assure.pojo.BinPojo;

public class BinServiceTest extends AbstractUnitTest {

	@Autowired
	private BinService binService;

	@Test
	public void testAdd() throws ApiException {

		binService.add();

	}

	@Test
	public void testGet() throws ApiException {

		BinPojo c = binService.add();
		BinPojo c1 = binService.add();

		BinPojo list = binService.get(c.getBinId());
		assertEquals(c.getBinId(), list.getBinId());

	}

	@Test
	public void testGetAll() throws ApiException {
		BinPojo c = binService.add();
		BinPojo c1 = binService.add();

		List<BinPojo> list = binService.getAll();
		assertEquals(2, list.size());
		assertEquals(c.getBinId(), list.get(0).getBinId());
		assertEquals(c1.getBinId(), list.get(1).getBinId());

	}

}

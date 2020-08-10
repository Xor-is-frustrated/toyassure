package com.increff.assure.dto;

import static org.junit.Assert.assertEquals;

import java.util.List;

import com.increff.commons.data.BinData;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.increff.assure.pojo.BinPojo;
import com.increff.assure.service.AbstractUnitTest;
import com.increff.assure.service.ApiException;
import com.increff.assure.service.BinService;

public class BinDtoTest extends AbstractUnitTest {

	@Autowired
	private BinDto binDto;

	@Autowired
	private BinService binService;

	@Test
	public void testAdd() throws ApiException {
		BinData list = binDto.add();
	}

	@Test
	public void testGetAll() throws ApiException {
		BinPojo c = binService.add();
		BinPojo c1 = binService.add();

		List<BinData> list = binDto.getAll();

		assertEquals(2, list.size());
		assertEquals(c.getBinId(), list.get(0).getBinId());
		assertEquals(c1.getBinId(), list.get(1).getBinId());

	}

	@Test
	public void testGet() throws ApiException {
		BinPojo c = binService.add();
		BinPojo c1 = binService.add();

		BinData list = binDto.get(c.getBinId());
		assertEquals(c.getBinId(), list.getBinId());
	}
	
}

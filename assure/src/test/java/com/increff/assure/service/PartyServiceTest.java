package com.increff.assure.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.increff.assure.pojo.PartyPojo;
import com.increff.commons.enums.PartyType;

public class PartyServiceTest extends AbstractUnitTest {

	@Autowired
	private PartyService partyService;

	@Test
	public void testAdd() throws ApiException {
		PartyPojo c = new PartyPojo();
		c.setName("assure");
		c.setType(PartyType.CLIENT);

		partyService.add(c);

	}

	@Test
	public void testGet() throws ApiException {
		PartyPojo c = new PartyPojo();
		c.setName("assure");
		c.setType(PartyType.CLIENT);
		partyService.add(c);

		PartyPojo c1 = new PartyPojo();
		c1.setName("spring");
		c1.setType(PartyType.CUSTOMER);
		partyService.add(c1);

		PartyPojo list = partyService.get(c.getId());
		assertEquals(c.getName(), list.getName());
		assertEquals(c.getType(), list.getType());
	}

	@Test
	public void testGetAll() throws ApiException {
		PartyPojo c = new PartyPojo();
		c.setName("assure");
		c.setType(PartyType.CLIENT);
		partyService.add(c);

		PartyPojo c1 = new PartyPojo();
		c1.setName("spring");
		c1.setType(PartyType.CUSTOMER);
		partyService.add(c1);

		List<PartyPojo> list = partyService.getAll();

		assertEquals(2, list.size());
		assertEquals(c.getName(), list.get(0).getName());
		assertEquals(c.getType(), list.get(0).getType());
		assertEquals(c1.getName(), list.get(1).getName());
		assertEquals(c1.getType(), list.get(1).getType());
	}

}

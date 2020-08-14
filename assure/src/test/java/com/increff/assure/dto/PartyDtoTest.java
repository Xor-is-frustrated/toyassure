package com.increff.assure.dto;

import static org.junit.Assert.assertEquals;

import java.util.List;

import com.increff.commons.data.PartyData;
import com.increff.commons.form.PartyForm;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


import com.increff.assure.pojo.PartyPojo;
import com.increff.commons.enums.PartyType;
import com.increff.assure.service.AbstractUnitTest;
import com.increff.assure.service.ApiException;
import com.increff.assure.service.PartyService;

public class PartyDtoTest extends AbstractUnitTest {
	
	@Autowired
	private PartyDto partyDto;
	
	@Autowired
	private PartyService service;
	
	@Test
	public void testAdd() throws ApiException {
		
		PartyForm c = new PartyForm();
		c.setName("assure");
		c.setType(PartyType.CLIENT);
		
		PartyData list= partyDto.add(c);

		
		
	}
	
	@Test
	public void testGetAll() throws ApiException {
		PartyPojo c = new PartyPojo();
		c.setName("assure");
		c.setType(PartyType.CLIENT);
		service.add(c);

		PartyPojo c1 = new PartyPojo();
		c1.setName("spring");
		c1.setType(PartyType.CUSTOMER);
		service.add(c1);

		List<PartyData>list= partyDto.getAll();
		
		assertEquals(2, list.size());
		assertEquals(c.getName(), list.get(0).getName());
//		assertEquals(c.getType(), list.get(0).getType());
		assertEquals(c1.getName(), list.get(1).getName());
//		assertEquals(c1.getType(), list.get(1).getType());
		
	}
	
	@Test
	public void testGet() throws ApiException {
		PartyPojo c = new PartyPojo();
		c.setName("assure");
		c.setType(PartyType.CLIENT);
		service.add(c);

		PartyPojo c1 = new PartyPojo();
		c1.setName("spring");
		c1.setType(PartyType.CUSTOMER);
		service.add(c1);

		PartyData list = partyDto.get(c.getId());
		assertEquals(c.getName(), list.getName());

	}

}

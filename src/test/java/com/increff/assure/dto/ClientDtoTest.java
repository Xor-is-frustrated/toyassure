package com.increff.assure.dto;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.increff.assure.model.ClientData;
import com.increff.assure.model.ClientForm;
import com.increff.assure.pojo.ClientPojo;
import com.increff.assure.pojo.ClientType;
import com.increff.assure.service.AbstractUnitTest;
import com.increff.assure.service.ApiException;
import com.increff.assure.service.ClientService;

public class ClientDtoTest  extends AbstractUnitTest {
	
	@Autowired
	private ClientDto clientDto;
	
	@Autowired
	private ClientService service;
	
	@Test
	public void testAdd() throws ApiException {
		
		ClientForm c = new ClientForm();
		c.setName("assure");
		c.setType(ClientType.CLIENT);
		
		ClientData list= clientDto.add(c);
		assertEquals(c.getName(), list.getName());
		assertEquals(c.getType(), list.getType());
		
		
	}
	
	@Test
	public void testGetAll() throws ApiException {
		ClientPojo c = new ClientPojo();
		c.setName("assure");
		c.setType(ClientType.CLIENT);
		service.add(c);

		ClientPojo c1 = new ClientPojo();
		c1.setName("spring");
		c1.setType(ClientType.CUSTOMER);
		service.add(c1);

		List<ClientData>list= clientDto.getAll();
		
		assertEquals(2, list.size());
		assertEquals(c.getName(), list.get(0).getName());
		assertEquals(c.getType(), list.get(0).getType());
		assertEquals(c1.getName(), list.get(1).getName());
		assertEquals(c1.getType(), list.get(1).getType());
		
	}
	
	@Test
	public void testGet() throws ApiException {
		ClientPojo c = new ClientPojo();
		c.setName("assure");
		c.setType(ClientType.CLIENT);
		service.add(c);

		ClientPojo c1 = new ClientPojo();
		c1.setName("spring");
		c1.setType(ClientType.CUSTOMER);
		service.add(c1);

		ClientData list = clientDto.get(c.getId());
		assertEquals(c.getName(), list.getName());
		assertEquals(c.getType(), list.getType());
	}

}

package com.increff.assure.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.increff.assure.pojo.ClientPojo;
import com.increff.assure.pojo.ClientType;

public class ClientServiceTest extends AbstractUnitTest {

	@Autowired
	private ClientService clientService;

	@Test
	public void testAdd() throws ApiException {
		ClientPojo c = new ClientPojo();
		c.setName("assure");
		c.setType(ClientType.CLIENT);

		clientService.add(c);

	}

	@Test
	public void testGet() throws ApiException {
		ClientPojo c = new ClientPojo();
		c.setName("assure");
		c.setType(ClientType.CLIENT);
		clientService.add(c);

		ClientPojo c1 = new ClientPojo();
		c1.setName("spring");
		c1.setType(ClientType.CUSTOMER);
		clientService.add(c1);

		ClientPojo list = clientService.get(c.getId());
		assertEquals(c.getName(), list.getName());
		assertEquals(c.getType(), list.getType());
	}

	@Test
	public void testGetAll() throws ApiException {
		ClientPojo c = new ClientPojo();
		c.setName("assure");
		c.setType(ClientType.CLIENT);
		clientService.add(c);

		ClientPojo c1 = new ClientPojo();
		c1.setName("spring");
		c1.setType(ClientType.CUSTOMER);
		clientService.add(c1);

		List<ClientPojo> list = clientService.getAll();

		assertEquals(2, list.size());
		assertEquals(c.getName(), list.get(0).getName());
		assertEquals(c.getType(), list.get(0).getType());
		assertEquals(c1.getName(), list.get(1).getName());
		assertEquals(c1.getType(), list.get(1).getType());
	}

}

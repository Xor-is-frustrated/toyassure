package com.increff.assure.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.increff.assure.pojo.ClientPojo;
import com.increff.assure.pojo.ClientType;
import com.increff.assure.service.AbstractUnitTest;


public class ClientDaoTest  extends AbstractUnitTest{

	@Autowired
	private ClientDao dao;

	@Test
	public void testInsert() {
		ClientPojo c = new ClientPojo();
		c.setName("assure");
		c.setType(ClientType.CLIENT);

		dao.insert(c);
		
	}
	
	@Test
	public void testSelectAll() {
		ClientPojo c = new ClientPojo();
		c.setName("assure");
		c.setType(ClientType.CLIENT);
		dao.insert(c);
		
		ClientPojo c1 = new ClientPojo();
		c1.setName("spring");
		c1.setType(ClientType.CUSTOMER);
		dao.insert(c1);
		
		List<ClientPojo> list = dao.selectAll();

		assertEquals(2, list.size());
		assertEquals(c.getName(), list.get(0).getName());
		assertEquals(c.getType(), list.get(0).getType());
		assertEquals(c1.getName(), list.get(1).getName());
		assertEquals(c1.getType(), list.get(1).getType());
	}
	
	@Test
	public void testById() {
		ClientPojo c = new ClientPojo();
		c.setName("assure");
		c.setType(ClientType.CLIENT);
		dao.insert(c);
		
		ClientPojo c1 = new ClientPojo();
		c1.setName("spring");
		c1.setType(ClientType.CUSTOMER);
		dao.insert(c1);
		
		ClientPojo list = dao.select(c.getId());
		assertEquals(c.getName(), list.getName());
		assertEquals(c.getType(), list.getType());
		
	}
	
	
}

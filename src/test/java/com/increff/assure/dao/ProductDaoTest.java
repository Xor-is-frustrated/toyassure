package com.increff.assure.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.increff.assure.pojo.ClientPojo;
import com.increff.assure.pojo.ClientType;
import com.increff.assure.pojo.ProductPojo;
import com.increff.assure.service.AbstractUnitTest;

public class ProductDaoTest extends AbstractUnitTest {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private ClientDao clientDao;

	@Test
	public void testInsert() {

		ClientPojo client = new ClientPojo();
		client.setName("assure");
		client.setType(ClientType.CLIENT);

		clientDao.insert(client);

		ProductPojo c = new ProductPojo();
		c.setName("assure");
		c.setBrandId("brand");
		c.setClientSkuId("clientsku");
		c.setDescription("this is description");
		c.setMrp(1.1);
		c.setClient(client);

		productDao.insert(c);

	}

	@Test
	public void testSelectAll() {
		ClientPojo client = new ClientPojo();
		client.setName("assure");
		client.setType(ClientType.CLIENT); 

		clientDao.insert(client);

		ProductPojo c = new ProductPojo();
		c.setName("assure");
		c.setBrandId("brand");
		c.setClientSkuId("clientsku");
		c.setDescription("this is description");
		c.setMrp(1.1);
		c.setClient(client);

		productDao.insert(c);

		ClientPojo client1 = new ClientPojo();
		client1.setName("assure1");
		client1.setType(ClientType.CLIENT);

		clientDao.insert(client1);

		ProductPojo c1 = new ProductPojo();
		c1.setName("assure");
		c1.setBrandId("brand");
		c1.setClientSkuId("clientsku");
		c1.setDescription("this is description");
		c1.setMrp(1.1);
		c1.setClient(client1);

		productDao.insert(c1);

		List<ProductPojo> list = productDao.selectAll();

		assertEquals(2, list.size());

		assertEquals(client, list.get(0).getClient());
		assertEquals(c.getName(), list.get(0).getName());
		assertEquals(c.getMrp(), list.get(0).getMrp());
		assertEquals(c.getDescription(), list.get(0).getDescription());
		assertEquals(c.getBrandId(), list.get(0).getBrandId());
		assertEquals(c.getClientSkuId(), list.get(0).getClientSkuId());
		assertEquals(c.getGlobalSkuId(), list.get(0).getGlobalSkuId());

		assertEquals(client1, list.get(1).getClient());
		assertEquals(c1.getName(), list.get(1).getName());
		assertEquals(c1.getMrp(), list.get(1).getMrp());
		assertEquals(c1.getDescription(), list.get(1).getDescription());
		assertEquals(c1.getBrandId(), list.get(1).getBrandId());
		assertEquals(c1.getClientSkuId(), list.get(1).getClientSkuId());
		assertEquals(c1.getGlobalSkuId(), list.get(1).getGlobalSkuId());

	}

	@Test
	public void testById() {
		ClientPojo client = new ClientPojo();
		client.setName("assure");
		client.setType(ClientType.CLIENT);

		clientDao.insert(client);

		ProductPojo c = new ProductPojo();
		c.setName("assure");
		c.setBrandId("brand");
		c.setClientSkuId("clientsku");
		c.setDescription("this is description");
		c.setMrp(1.1);
		c.setClient(client);

		productDao.insert(c);

		ClientPojo client1 = new ClientPojo();
		client1.setName("assure1");
		client1.setType(ClientType.CLIENT);

		clientDao.insert(client1);

		ProductPojo c1 = new ProductPojo();
		c1.setName("assure");
		c1.setBrandId("brand");
		c1.setClientSkuId("clientsku");
		c1.setDescription("this is description");
		c1.setMrp(1.1);
		c1.setClient(client1);

		productDao.insert(c1);

		ProductPojo list = productDao.select(c.getGlobalSkuId());

		assertEquals(client, list.getClient());
		assertEquals(c.getName(), list.getName());
		assertEquals(c.getMrp(), list.getMrp());
		assertEquals(c.getDescription(), list.getDescription());
		assertEquals(c.getBrandId(), list.getBrandId());
		assertEquals(c.getClientSkuId(), list.getClientSkuId());
		assertEquals(c.getGlobalSkuId(), list.getGlobalSkuId());

	}

	@Test
	public void testSelectByClientSkuIdAndClient() {
		ClientPojo client = new ClientPojo();
		client.setName("assure");
		client.setType(ClientType.CLIENT);

		clientDao.insert(client);

		ProductPojo c = new ProductPojo();
		c.setName("assure");
		c.setBrandId("brand");
		c.setClientSkuId("clientsku");
		c.setDescription("this is description");
		c.setMrp(1.1);
		c.setClient(client);

		productDao.insert(c);

		ClientPojo client1 = new ClientPojo();
		client1.setName("assure1");
		client1.setType(ClientType.CLIENT);

		clientDao.insert(client1);

		ProductPojo c1 = new ProductPojo();
		c1.setName("assure");
		c1.setBrandId("brand");
		c1.setClientSkuId("clientsku");
		c1.setDescription("this is description");
		c1.setMrp(1.1);
		c1.setClient(client1);

		productDao.insert(c1);

		ProductPojo list = productDao.selectByClientAndClientSkuId("clientsku", client);
		assertEquals(client, list.getClient());
		assertEquals(c.getName(), list.getName());
		assertEquals(c.getMrp(), list.getMrp());
		assertEquals(c.getDescription(), list.getDescription());
		assertEquals(c.getBrandId(), list.getBrandId());
		assertEquals(c.getClientSkuId(), list.getClientSkuId());
		assertEquals(c.getGlobalSkuId(), list.getGlobalSkuId());

	}

}

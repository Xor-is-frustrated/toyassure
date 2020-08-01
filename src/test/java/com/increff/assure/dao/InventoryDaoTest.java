package com.increff.assure.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.increff.assure.pojo.ClientPojo;
import com.increff.assure.pojo.ClientType;
import com.increff.assure.pojo.InventoryPojo;
import com.increff.assure.pojo.ProductPojo;
import com.increff.assure.service.AbstractUnitTest;

public class InventoryDaoTest extends AbstractUnitTest {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private ClientDao clientDao;

	@Autowired
	private InventoryDao inventoryDao;

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

		InventoryPojo inv = new InventoryPojo();
		inv.setProduct(c);
		inv.setAllocatedQuantity(Long.valueOf(10));
		inv.setAvailableQuantity(Long.valueOf(10));
		inv.setFulfilledQuantity(Long.valueOf(10));

		inventoryDao.insert(inv);

	}

	@Test
	public void testSelect() {
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

		InventoryPojo inv = new InventoryPojo();
		inv.setProduct(c);
		inv.setAllocatedQuantity(Long.valueOf(10));
		inv.setAvailableQuantity(Long.valueOf(10));
		inv.setFulfilledQuantity(Long.valueOf(10));

		inventoryDao.insert(inv);

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

		InventoryPojo inv1 = new InventoryPojo();
		inv1.setProduct(c1);
		inv1.setAllocatedQuantity(Long.valueOf(10));
		inv1.setAvailableQuantity(Long.valueOf(10));
		inv1.setFulfilledQuantity(Long.valueOf(10));

		inventoryDao.insert(inv1);

		InventoryPojo list = inventoryDao.select(inv.getId());

		assertEquals(inv.getProduct(), list.getProduct());
		assertEquals(inv.getAllocatedQuantity(), list.getAllocatedQuantity());
		assertEquals(inv.getAvailableQuantity(), list.getAvailableQuantity());
		assertEquals(inv.getFulfilledQuantity(), list.getFulfilledQuantity());

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

		InventoryPojo inv = new InventoryPojo();
		inv.setProduct(c);
		inv.setAllocatedQuantity(Long.valueOf(10));
		inv.setAvailableQuantity(Long.valueOf(10));
		inv.setFulfilledQuantity(Long.valueOf(10));

		inventoryDao.insert(inv);

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

		InventoryPojo inv1 = new InventoryPojo();
		inv1.setProduct(c1);
		inv1.setAllocatedQuantity(Long.valueOf(10));
		inv1.setAvailableQuantity(Long.valueOf(10));
		inv1.setFulfilledQuantity(Long.valueOf(10));

		inventoryDao.insert(inv1);

		List<InventoryPojo> list = inventoryDao.selectAll();

		assertEquals(2, list.size());

		assertEquals(inv.getProduct(), list.get(0).getProduct());
		assertEquals(inv.getAllocatedQuantity(), list.get(0).getAllocatedQuantity());
		assertEquals(inv.getAvailableQuantity(), list.get(0).getAvailableQuantity());
		assertEquals(inv.getFulfilledQuantity(), list.get(0).getFulfilledQuantity());

		assertEquals(inv1.getProduct(), list.get(1).getProduct());
		assertEquals(inv1.getAllocatedQuantity(), list.get(1).getAllocatedQuantity());
		assertEquals(inv1.getAvailableQuantity(), list.get(1).getAvailableQuantity());
		assertEquals(inv1.getFulfilledQuantity(), list.get(1).getFulfilledQuantity());

	}

}

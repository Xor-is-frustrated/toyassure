package com.increff.assure.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.increff.assure.pojo.PartyPojo;
import com.increff.commons.enums.PartyType;
import com.increff.assure.pojo.InventoryPojo;
import com.increff.assure.pojo.ProductPojo;

public class InventoryServiceTest extends AbstractUnitTest {

	@Autowired
	private PartyService partyService;

	@Autowired
	private ProductService productService;

	@Autowired
	private InventoryService inventoryService;

	@Test
	public void testAdd() throws ApiException {
		PartyPojo client = new PartyPojo();
		client.setName("assure");
		client.setType(PartyType.CLIENT);

		partyService.add(client);

		ProductPojo c = new ProductPojo();
		c.setName("assure");
		c.setBrandId("brand");
		c.setClientSkuId("clientsku");
		c.setDescription("this is description");
		c.setMrp(1.1);
		c.setParty(client);

		productService.add(c);

		InventoryPojo inv = new InventoryPojo();
		inv.setProduct(c);
		inv.setAllocatedQuantity(Long.valueOf(10));
		inv.setAvailableQuantity(Long.valueOf(10));
		inv.setFulfilledQuantity(Long.valueOf(10));

		inventoryService.add(inv);
	}

	@Test
	public void testGetById() throws ApiException {
		PartyPojo client = new PartyPojo();
		client.setName("assure");
		client.setType(PartyType.CLIENT);

		partyService.add(client);

		ProductPojo c = new ProductPojo();
		c.setName("assure");
		c.setBrandId("brand");
		c.setClientSkuId("clientsku");
		c.setDescription("this is description");
		c.setMrp(1.1);
		c.setParty(client);

		productService.add(c);

		InventoryPojo inv = new InventoryPojo();
		inv.setProduct(c);
		inv.setAllocatedQuantity(Long.valueOf(10));
		inv.setAvailableQuantity(Long.valueOf(10));
		inv.setFulfilledQuantity(Long.valueOf(10));

		inventoryService.add(inv);

		PartyPojo client1 = new PartyPojo();
		client1.setName("assure1");
		client1.setType(PartyType.CLIENT);

		partyService.add(client1);

		ProductPojo c1 = new ProductPojo();
		c1.setName("assure");
		c1.setBrandId("brand");
		c1.setClientSkuId("clientsku");
		c1.setDescription("this is description");
		c1.setMrp(1.1);
		c1.setParty(client1);

		productService.add(c1);

		InventoryPojo inv1 = new InventoryPojo();
		inv1.setProduct(c1);
		inv1.setAllocatedQuantity(Long.valueOf(10));
		inv1.setAvailableQuantity(Long.valueOf(10));
		inv1.setFulfilledQuantity(Long.valueOf(10));

		inventoryService.add(inv1);

		InventoryPojo list = inventoryService.getByGlobalSkuId(inv.getProduct().getGlobalSkuId());

		assertEquals(inv.getProduct(), list.getProduct());
		assertEquals(inv.getAllocatedQuantity(), list.getAllocatedQuantity());
		assertEquals(inv.getAvailableQuantity(), list.getAvailableQuantity());
		assertEquals(inv.getFulfilledQuantity(), list.getFulfilledQuantity());
	}

	@Test
	public void testSelectAll() throws ApiException {
		PartyPojo client = new PartyPojo();
		client.setName("assure");
		client.setType(PartyType.CLIENT);

		partyService.add(client);

		ProductPojo c = new ProductPojo();
		c.setName("assure");
		c.setBrandId("brand");
		c.setClientSkuId("clientsku");
		c.setDescription("this is description");
		c.setMrp(1.1);
		c.setParty(client);

		productService.add(c);

		InventoryPojo inv = new InventoryPojo();
		inv.setProduct(c);
		inv.setAllocatedQuantity(Long.valueOf(10));
		inv.setAvailableQuantity(Long.valueOf(10));
		inv.setFulfilledQuantity(Long.valueOf(10));

		inventoryService.add(inv);

		PartyPojo client1 = new PartyPojo();
		client1.setName("assure1");
		client1.setType(PartyType.CLIENT);

		partyService.add(client1);

		ProductPojo c1 = new ProductPojo();
		c1.setName("assure");
		c1.setBrandId("brand");
		c1.setClientSkuId("clientsku");
		c1.setDescription("this is description");
		c1.setMrp(1.1);
		c1.setParty(client1);

		productService.add(c1);

		InventoryPojo inv1 = new InventoryPojo();
		inv1.setProduct(c1);
		inv1.setAllocatedQuantity(Long.valueOf(10));
		inv1.setAvailableQuantity(Long.valueOf(10));
		inv1.setFulfilledQuantity(Long.valueOf(10));

		inventoryService.add(inv1);

		List<InventoryPojo> list = inventoryService.getAll();

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

	@Test
	public void testByProduct() throws ApiException {
		PartyPojo client = new PartyPojo();
		client.setName("assure");
		client.setType(PartyType.CLIENT);

		partyService.add(client);

		ProductPojo c = new ProductPojo();
		c.setName("assure");
		c.setBrandId("brand");
		c.setClientSkuId("clientsku");
		c.setDescription("this is description");
		c.setMrp(1.1);
		c.setParty(client);

		productService.add(c);

		InventoryPojo inv = new InventoryPojo();
		inv.setProduct(c);
		inv.setAllocatedQuantity(Long.valueOf(10));
		inv.setAvailableQuantity(Long.valueOf(10));
		inv.setFulfilledQuantity(Long.valueOf(10));

		inventoryService.add(inv);

		PartyPojo client1 = new PartyPojo();
		client1.setName("assure1");
		client1.setType(PartyType.CLIENT);

		partyService.add(client1);

		ProductPojo c1 = new ProductPojo();
		c1.setName("assure");
		c1.setBrandId("brand");
		c1.setClientSkuId("clientsku");
		c1.setDescription("this is description");
		c1.setMrp(1.1);
		c1.setParty(client1);

		productService.add(c1);

		InventoryPojo inv1 = new InventoryPojo();
		inv1.setProduct(c1);
		inv1.setAllocatedQuantity(Long.valueOf(10));
		inv1.setAvailableQuantity(Long.valueOf(10));
		inv1.setFulfilledQuantity(Long.valueOf(10));

		inventoryService.add(inv1);

		InventoryPojo list = inventoryService.getByGlobalSkuId(c.getGlobalSkuId());

		assertEquals(inv.getProduct(), list.getProduct());
		assertEquals(inv.getAllocatedQuantity(), list.getAllocatedQuantity());
		assertEquals(inv.getAvailableQuantity(), list.getAvailableQuantity());
		assertEquals(inv.getFulfilledQuantity(), list.getFulfilledQuantity());
	}

	@Test
	public void testUpdateQuantities() throws ApiException {
		PartyPojo client = new PartyPojo();
		client.setName("assure");
		client.setType(PartyType.CLIENT);

		partyService.add(client);

		ProductPojo c = new ProductPojo();
		c.setName("assure");
		c.setBrandId("brand");
		c.setClientSkuId("clientsku");
		c.setDescription("this is description");
		c.setMrp(1.1);
		c.setParty(client);

		productService.add(c);

		InventoryPojo inv = new InventoryPojo();
		inv.setProduct(c);
		inv.setAllocatedQuantity(Long.valueOf(10));
		inv.setAvailableQuantity(Long.valueOf(10));
		inv.setFulfilledQuantity(Long.valueOf(10));

		inventoryService.add(inv);

		PartyPojo client1 = new PartyPojo();
		client1.setName("assure1");
		client1.setType(PartyType.CLIENT);

		partyService.add(client1);

		ProductPojo c1 = new ProductPojo();
		c1.setName("assure");
		c1.setBrandId("brand");
		c1.setClientSkuId("clientsku");
		c1.setDescription("this is description");
		c1.setMrp(1.1);
		c1.setParty(client1);

		productService.add(c1);

		InventoryPojo inv1 = new InventoryPojo();
		inv1.setProduct(c1);
		inv1.setAllocatedQuantity(Long.valueOf(10));
		inv1.setAvailableQuantity(Long.valueOf(10));
		inv1.setFulfilledQuantity(Long.valueOf(10));

		inventoryService.add(inv1);
		Long availableQuantity = Long.valueOf(11);
		Long allocatedQuantity = Long.valueOf(11);
		Long fulfilledQuantity = Long.valueOf(11);
		inventoryService.updateQuantities(inv.getProduct().getGlobalSkuId(), availableQuantity, allocatedQuantity, fulfilledQuantity);

		InventoryPojo list = inventoryService.getByGlobalSkuId(inv.getProduct().getGlobalSkuId());

		assertEquals(inv.getProduct(), list.getProduct());
		assertEquals(allocatedQuantity, list.getAllocatedQuantity());
		assertEquals(availableQuantity, list.getAvailableQuantity());
		assertEquals(fulfilledQuantity, list.getFulfilledQuantity());
	}

}

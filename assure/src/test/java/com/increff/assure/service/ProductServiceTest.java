package com.increff.assure.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.increff.assure.pojo.PartyPojo;
import com.increff.commons.enums.PartyType;
import com.increff.assure.pojo.ProductPojo;

public class ProductServiceTest extends AbstractUnitTest {

	@Autowired
	private PartyService partyService;

	@Autowired
	private ProductService productService;

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
	}

	@Test
	public void testById() throws ApiException {
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

		ProductPojo list = productService.get(c.getGlobalSkuId());

		assertEquals(client, list.getParty());
		assertEquals(c.getName(), list.getName());
		assertEquals(c.getMrp(), list.getMrp());
		assertEquals(c.getDescription(), list.getDescription());
		assertEquals(c.getBrandId(), list.getBrandId());
		assertEquals(c.getClientSkuId(), list.getClientSkuId());
		assertEquals(c.getGlobalSkuId(), list.getGlobalSkuId());

	}
	
	@Test
	public void testgetByClientIdAndClientSkuId() throws ApiException {
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

		ProductPojo list = productService.getByClientIdAndClientSkuId(c.getClientSkuId(), client.getId());
		
		assertEquals(client, list.getParty());
		assertEquals(c.getName(), list.getName());
		assertEquals(c.getMrp(), list.getMrp());
		assertEquals(c.getDescription(), list.getDescription());
		assertEquals(c.getBrandId(), list.getBrandId());
		assertEquals(c.getClientSkuId(), list.getClientSkuId());
		assertEquals(c.getGlobalSkuId(), list.getGlobalSkuId());

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

		List<ProductPojo> list = productService.getAll();

		assertEquals(2, list.size());

		assertEquals(client, list.get(0).getParty());
		assertEquals(c.getName(), list.get(0).getName());
		assertEquals(c.getMrp(), list.get(0).getMrp());
		assertEquals(c.getDescription(), list.get(0).getDescription());
		assertEquals(c.getBrandId(), list.get(0).getBrandId());
		assertEquals(c.getClientSkuId(), list.get(0).getClientSkuId());
		assertEquals(c.getGlobalSkuId(), list.get(0).getGlobalSkuId());

		assertEquals(client1, list.get(1).getParty());
		assertEquals(c1.getName(), list.get(1).getName());
		assertEquals(c1.getMrp(), list.get(1).getMrp());
		assertEquals(c1.getDescription(), list.get(1).getDescription());
		assertEquals(c1.getBrandId(), list.get(1).getBrandId());
		assertEquals(c1.getClientSkuId(), list.get(1).getClientSkuId());
		assertEquals(c1.getGlobalSkuId(), list.get(1).getGlobalSkuId());

	}

	@Test
	public void testUpdate() throws ApiException {
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

		c.setBrandId("brand change");
		c.setName("assure change");
		c.setDescription("description change");

		productService.update(c.getGlobalSkuId(), c.getName(),c.getDescription(),c.getMrp(),c.getBrandId());

		assertEquals("brand change", c.getBrandId());
		assertEquals("assure change", c.getName());
		assertEquals("description change", c.getDescription());

	}

}

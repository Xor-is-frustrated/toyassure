package com.increff.assure.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.increff.assure.pojo.BinPojo;
import com.increff.assure.pojo.BinSkuPojo;
import com.increff.assure.pojo.PartyPojo;
import com.increff.commons.enums.PartyType;
import com.increff.assure.pojo.ProductPojo;

public class BinSkuServiceTest extends AbstractUnitTest {

	@Autowired
	private PartyService partyService;

	@Autowired
	private ProductService productService;

	@Autowired
	private BinService binService;

	@Autowired
	private BinSkuService binSkuService;

	@Test
	public void testAdd() throws ApiException {

		PartyPojo client = new PartyPojo();
		client.setName("assure");
		client.setType(PartyType.CLIENT);

		partyService.add(client);

		ProductPojo product = new ProductPojo();
		product.setName("assure");
		product.setBrandId("brand");
		product.setClientSkuId("clientsku");
		product.setDescription("this is description");
		product.setMrp(1.1);
		product.setParty(client);

		productService.add(product);

		BinPojo bin = binService.add();

		BinSkuPojo pojo = new BinSkuPojo();
		pojo.setBin(bin);
		pojo.setProduct(product);
		pojo.setQuantity(Long.valueOf(11));

		binSkuService.add(pojo);
	}

	@Test
	public void testGetAll() throws ApiException {
		PartyPojo client = new PartyPojo();
		client.setName("assure");
		client.setType(PartyType.CLIENT);

		partyService.add(client);

		ProductPojo product = new ProductPojo();
		product.setName("assure");
		product.setBrandId("brand");
		product.setClientSkuId("clientsku");
		product.setDescription("this is description");
		product.setMrp(1.1);
		product.setParty(client);

		productService.add(product);

		BinPojo bin = binService.add();

		BinSkuPojo pojo = new BinSkuPojo();
		pojo.setBin(bin);
		pojo.setProduct(product);
		pojo.setQuantity(Long.valueOf(11));

		binSkuService.add(pojo);

		PartyPojo client1 = new PartyPojo();
		client1.setName("assure1");
		client1.setType(PartyType.CLIENT);

		partyService.add(client1);

		ProductPojo product1 = new ProductPojo();
		product1.setName("assure");
		product1.setBrandId("brand");
		product1.setClientSkuId("client1sku");
		product1.setDescription("this is description");
		product1.setMrp(1.1);
		product1.setParty(client1);

		productService.add(product1);

		BinPojo bin1 = binService.add();

		BinSkuPojo pojo1 = new BinSkuPojo();
		pojo1.setBin(bin1);
		pojo1.setProduct(product1);
		pojo1.setQuantity(Long.valueOf(11));

		binSkuService.add(pojo1);

		List<BinSkuPojo> list = binSkuService.getAll();

		assertEquals(2, list.size());
		assertEquals(product, list.get(0).getProduct());
		assertEquals(bin, list.get(0).getBin());
		assertEquals(pojo.getQuantity(), list.get(0).getQuantity());

		assertEquals(product1, list.get(1).getProduct());
		assertEquals(bin1, list.get(1).getBin());
		assertEquals(pojo1.getQuantity(), list.get(1).getQuantity());

	}

	@Test
	public void testGet() throws ApiException {
		PartyPojo client = new PartyPojo();
		client.setName("assure");
		client.setType(PartyType.CLIENT);

		partyService.add(client);

		ProductPojo product = new ProductPojo();
		product.setName("assure");
		product.setBrandId("brand");
		product.setClientSkuId("clientsku");
		product.setDescription("this is description");
		product.setMrp(1.1);
		product.setParty(client);

		productService.add(product);

		BinPojo bin = binService.add();

		BinSkuPojo pojo = new BinSkuPojo();
		pojo.setBin(bin);
		pojo.setProduct(product);
		pojo.setQuantity(Long.valueOf(11));

		binSkuService.add(pojo);

		PartyPojo client1 = new PartyPojo();
		client1.setName("assure1");
		client1.setType(PartyType.CLIENT);

		partyService.add(client1);

		ProductPojo product1 = new ProductPojo();
		product1.setName("assure");
		product1.setBrandId("brand");
		product1.setClientSkuId("client1sku");
		product1.setDescription("this is description");
		product1.setMrp(1.1);
		product1.setParty(client1);

		productService.add(product1);

		BinPojo bin1 = binService.add();

		BinSkuPojo pojo1 = new BinSkuPojo();
		pojo1.setBin(bin1);
		pojo1.setProduct(product1);
		pojo1.setQuantity(Long.valueOf(11));

		binSkuService.add(pojo1);

		BinSkuPojo list = binSkuService.get(pojo.getId());

		assertEquals(product, list.getProduct());
		assertEquals(bin, list.getBin());
		assertEquals(pojo.getQuantity(), list.getQuantity());

	}

	@Test
	public void testUpdate() throws ApiException {
		PartyPojo client = new PartyPojo();
		client.setName("assure");
		client.setType(PartyType.CLIENT);

		partyService.add(client);

		ProductPojo product = new ProductPojo();
		product.setName("assure");
		product.setBrandId("brand");
		product.setClientSkuId("clientsku");
		product.setDescription("this is description");
		product.setMrp(1.1);
		product.setParty(client);

		productService.add(product);

		BinPojo bin = binService.add();

		BinSkuPojo pojo = new BinSkuPojo();
		pojo.setBin(bin);
		pojo.setProduct(product);
		pojo.setQuantity(Long.valueOf(11));

		binSkuService.add(pojo);

		pojo.setQuantity(Long.valueOf(21));
		BinSkuPojo list = binSkuService.updateQuantity(pojo.getId(), Long.valueOf(21));
		assertEquals(pojo.getQuantity(), list.getQuantity());

	}

}

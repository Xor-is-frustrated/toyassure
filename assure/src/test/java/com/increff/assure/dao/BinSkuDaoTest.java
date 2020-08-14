package com.increff.assure.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import com.increff.commons.enums.PartyType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.increff.assure.pojo.BinPojo;
import com.increff.assure.pojo.BinSkuPojo;
import com.increff.assure.pojo.PartyPojo;

import com.increff.assure.pojo.ProductPojo;
import com.increff.assure.service.AbstractUnitTest;

public class BinSkuDaoTest extends AbstractUnitTest {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private PartyDao partyDao;

	@Autowired
	private BinDao binDao;

	@Autowired
	private BinSkuDao binSkuDao;

	@Test
	public void testInsert() {

		PartyPojo client = new PartyPojo();
		client.setName("assure");
		client.setType(PartyType.CLIENT);

		partyDao.insert(client);

		ProductPojo product = new ProductPojo();
		product.setName("assure");
		product.setBrandId("brand");
		product.setClientSkuId("clientsku");
		product.setDescription("this is description");
		product.setMrp(1.1);
		product.setParty(client);

		productDao.insert(product);

		BinPojo bin = new BinPojo();
		binDao.insert(bin);

		BinSkuPojo pojo = new BinSkuPojo();
		pojo.setBin(bin);
		pojo.setProduct(product);
		pojo.setQuantity(Long.valueOf(11));

		binSkuDao.insert(pojo);
	}

	@Test
	public void testSelectAll() {
		PartyPojo client = new PartyPojo();
		client.setName("assure");
		client.setType(PartyType.CLIENT);

		partyDao.insert(client);

		ProductPojo product = new ProductPojo();
		product.setName("assure");
		product.setBrandId("brand");
		product.setClientSkuId("clientsku");
		product.setDescription("this is description");
		product.setMrp(1.1);
		product.setParty(client);

		productDao.insert(product);

		BinPojo bin = new BinPojo();
		binDao.insert(bin);

		BinSkuPojo pojo = new BinSkuPojo();
		pojo.setBin(bin);
		pojo.setProduct(product);
		pojo.setQuantity(Long.valueOf(11));

		binSkuDao.insert(pojo);

		PartyPojo client1 = new PartyPojo();
		client1.setName("assure1");
		client1.setType(PartyType.CLIENT);

		partyDao.insert(client1);

		ProductPojo product1 = new ProductPojo();
		product1.setName("assure");
		product1.setBrandId("brand");
		product1.setClientSkuId("client1sku");
		product1.setDescription("this is description");
		product1.setMrp(1.1);
		product1.setParty(client1);

		productDao.insert(product1);

		BinPojo bin1 = new BinPojo();
		binDao.insert(bin1);

		BinSkuPojo pojo1 = new BinSkuPojo();
		pojo1.setBin(bin1);
		pojo1.setProduct(product1);
		pojo1.setQuantity(Long.valueOf(11));

		binSkuDao.insert(pojo1);

		List<BinSkuPojo> list = binSkuDao.selectAll();

		assertEquals(2, list.size());
		assertEquals(product, list.get(0).getProduct());
		assertEquals(bin, list.get(0).getBin());
		assertEquals(pojo.getQuantity(), list.get(0).getQuantity());

		assertEquals(product1, list.get(1).getProduct());
		assertEquals(bin1, list.get(1).getBin());
		assertEquals(pojo1.getQuantity(), list.get(1).getQuantity());

	}

	@Test
	public void testById() {
		PartyPojo client = new PartyPojo();
		client.setName("assure");
		client.setType(PartyType.CLIENT);

		partyDao.insert(client);

		ProductPojo product = new ProductPojo();
		product.setName("assure");
		product.setBrandId("brand");
		product.setClientSkuId("clientsku");
		product.setDescription("this is description");
		product.setMrp(1.1);
		product.setParty(client);

		productDao.insert(product);

		BinPojo bin = new BinPojo();
		binDao.insert(bin);

		BinSkuPojo pojo = new BinSkuPojo();
		pojo.setBin(bin);
		pojo.setProduct(product);
		pojo.setQuantity(Long.valueOf(11));

		binSkuDao.insert(pojo);

		PartyPojo client1 = new PartyPojo();
		client1.setName("assure1");
		client1.setType(PartyType.CLIENT);

		partyDao.insert(client1);

		ProductPojo product1 = new ProductPojo();
		product1.setName("assure");
		product1.setBrandId("brand");
		product1.setClientSkuId("client1sku");
		product1.setDescription("this is description");
		product1.setMrp(1.1);
		product1.setParty(client1);

		productDao.insert(product1);

		BinPojo bin1 = new BinPojo();
		binDao.insert(bin1);

		BinSkuPojo pojo1 = new BinSkuPojo();
		pojo1.setBin(bin1);
		pojo1.setProduct(product1);
		pojo1.setQuantity(Long.valueOf(11));

		binSkuDao.insert(pojo1);

		BinSkuPojo list = binSkuDao.select(pojo.getId());

		assertEquals(product, list.getProduct());
		assertEquals(bin, list.getBin());
		assertEquals(pojo.getQuantity(), list.getQuantity());

	}

	@Test
	public void testSelectByBinAndProduct() {
		PartyPojo client = new PartyPojo();
		client.setName("assure");
		client.setType(PartyType.CLIENT);

		partyDao.insert(client);

		ProductPojo product = new ProductPojo();
		product.setName("assure");
		product.setBrandId("brand");
		product.setClientSkuId("clientsku");
		product.setDescription("this is description");
		product.setMrp(1.1);
		product.setParty(client);

		productDao.insert(product);

		BinPojo bin = new BinPojo();
		binDao.insert(bin);

		BinSkuPojo pojo = new BinSkuPojo();
		pojo.setBin(bin);
		pojo.setProduct(product);
		pojo.setQuantity(Long.valueOf(11));

		binSkuDao.insert(pojo);

		PartyPojo client1 = new PartyPojo();
		client1.setName("assure1");
		client1.setType(PartyType.CLIENT);

		partyDao.insert(client1);

		ProductPojo product1 = new ProductPojo();
		product1.setName("assure");
		product1.setBrandId("brand");
		product1.setClientSkuId("client1sku");
		product1.setDescription("this is description");
		product1.setMrp(1.1);
		product1.setParty(client1);

		productDao.insert(product1);

		BinPojo bin1 = new BinPojo();
		binDao.insert(bin1);

		BinSkuPojo pojo1 = new BinSkuPojo();
		pojo1.setBin(bin1);
		pojo1.setProduct(product1);
		pojo1.setQuantity(Long.valueOf(11));

		binSkuDao.insert(pojo1);

		BinSkuPojo list = binSkuDao.selectByBinIdAndGlobalSkuId(bin.getBinId(), product.getGlobalSkuId());

		assertEquals(product, list.getProduct());
		assertEquals(bin, list.getBin());
		assertEquals(pojo.getQuantity(), list.getQuantity());

	}

}

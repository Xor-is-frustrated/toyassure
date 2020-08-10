package com.increff.assure.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.increff.assure.pojo.BinPojo;
import com.increff.assure.pojo.BinSkuPojo;
import com.increff.assure.pojo.ClientPojo;
import com.increff.commons.enums.ClientType;
import com.increff.assure.pojo.ProductPojo;
import com.increff.assure.service.AbstractUnitTest;

public class BinSkuDaoTest extends AbstractUnitTest {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private ClientDao clientDao;

	@Autowired
	private BinDao binDao;

	@Autowired
	private BinSkuDao binSkuDao;

	@Test
	public void testInsert() {

		ClientPojo client = new ClientPojo();
		client.setName("assure");
		client.setType(ClientType.CLIENT);

		clientDao.insert(client);

		ProductPojo product = new ProductPojo();
		product.setName("assure");
		product.setBrandId("brand");
		product.setClientSkuId("clientsku");
		product.setDescription("this is description");
		product.setMrp(1.1);
		product.setClient(client);

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
		ClientPojo client = new ClientPojo();
		client.setName("assure");
		client.setType(ClientType.CLIENT);

		clientDao.insert(client);

		ProductPojo product = new ProductPojo();
		product.setName("assure");
		product.setBrandId("brand");
		product.setClientSkuId("clientsku");
		product.setDescription("this is description");
		product.setMrp(1.1);
		product.setClient(client);

		productDao.insert(product);

		BinPojo bin = new BinPojo();
		binDao.insert(bin);

		BinSkuPojo pojo = new BinSkuPojo();
		pojo.setBin(bin);
		pojo.setProduct(product);
		pojo.setQuantity(Long.valueOf(11));

		binSkuDao.insert(pojo);

		ClientPojo client1 = new ClientPojo();
		client1.setName("assure1");
		client1.setType(ClientType.CLIENT);

		clientDao.insert(client1);

		ProductPojo product1 = new ProductPojo();
		product1.setName("assure");
		product1.setBrandId("brand");
		product1.setClientSkuId("client1sku");
		product1.setDescription("this is description");
		product1.setMrp(1.1);
		product1.setClient(client1);

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
		ClientPojo client = new ClientPojo();
		client.setName("assure");
		client.setType(ClientType.CLIENT);

		clientDao.insert(client);

		ProductPojo product = new ProductPojo();
		product.setName("assure");
		product.setBrandId("brand");
		product.setClientSkuId("clientsku");
		product.setDescription("this is description");
		product.setMrp(1.1);
		product.setClient(client);

		productDao.insert(product);

		BinPojo bin = new BinPojo();
		binDao.insert(bin);

		BinSkuPojo pojo = new BinSkuPojo();
		pojo.setBin(bin);
		pojo.setProduct(product);
		pojo.setQuantity(Long.valueOf(11));

		binSkuDao.insert(pojo);

		ClientPojo client1 = new ClientPojo();
		client1.setName("assure1");
		client1.setType(ClientType.CLIENT);

		clientDao.insert(client1);

		ProductPojo product1 = new ProductPojo();
		product1.setName("assure");
		product1.setBrandId("brand");
		product1.setClientSkuId("client1sku");
		product1.setDescription("this is description");
		product1.setMrp(1.1);
		product1.setClient(client1);

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
		ClientPojo client = new ClientPojo();
		client.setName("assure");
		client.setType(ClientType.CLIENT);

		clientDao.insert(client);

		ProductPojo product = new ProductPojo();
		product.setName("assure");
		product.setBrandId("brand");
		product.setClientSkuId("clientsku");
		product.setDescription("this is description");
		product.setMrp(1.1);
		product.setClient(client);

		productDao.insert(product);

		BinPojo bin = new BinPojo();
		binDao.insert(bin);

		BinSkuPojo pojo = new BinSkuPojo();
		pojo.setBin(bin);
		pojo.setProduct(product);
		pojo.setQuantity(Long.valueOf(11));

		binSkuDao.insert(pojo);

		ClientPojo client1 = new ClientPojo();
		client1.setName("assure1");
		client1.setType(ClientType.CLIENT);

		clientDao.insert(client1);

		ProductPojo product1 = new ProductPojo();
		product1.setName("assure");
		product1.setBrandId("brand");
		product1.setClientSkuId("client1sku");
		product1.setDescription("this is description");
		product1.setMrp(1.1);
		product1.setClient(client1);

		productDao.insert(product1);

		BinPojo bin1 = new BinPojo();
		binDao.insert(bin1);

		BinSkuPojo pojo1 = new BinSkuPojo();
		pojo1.setBin(bin1);
		pojo1.setProduct(product1);
		pojo1.setQuantity(Long.valueOf(11));

		binSkuDao.insert(pojo1);

		BinSkuPojo list = binSkuDao.selectByBinAndProduct(bin, product);

		assertEquals(product, list.getProduct());
		assertEquals(bin, list.getBin());
		assertEquals(pojo.getQuantity(), list.getQuantity());

	}

}

package com.increff.assure.dto;

import static org.junit.Assert.assertEquals;

import java.util.List;

import com.increff.assure.service.*;
import com.increff.commons.data.BinSkuData;
import com.increff.commons.form.BinSkuForm;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.increff.assure.pojo.BinPojo;
import com.increff.assure.pojo.BinSkuPojo;
import com.increff.assure.pojo.PartyPojo;
import com.increff.commons.enums.PartyType;
import com.increff.assure.pojo.InventoryPojo;
import com.increff.assure.pojo.ProductPojo;
import com.increff.assure.service.AbstractUnitTest;

public class BinSkuDtoTest extends AbstractUnitTest {

	@Autowired
	private BinSkuDto binSkuDto;

	@Autowired
	private PartyService partyService;

	@Autowired
	private ProductService productService;
	
	@Autowired
	private InventoryService inventoryService;

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

		BinSkuForm form = new BinSkuForm();
		form.setBinId(bin.getBinId());
		form.setClientName(client.getName());
		form.setClientSkuId(product.getClientSkuId());
		form.setQuantity(Long.valueOf(11));

		binSkuDto.add(form);

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

		List<BinSkuData> list = binSkuDto.getAll();

		assertEquals(2, list.size());
		assertEquals(product.getName(), list.get(0).getProductName());
		assertEquals(bin.getBinId(), list.get(0).getBinId());
		assertEquals(pojo.getQuantity(), list.get(0).getQuantity());

		assertEquals(product1.getName(), list.get(1).getProductName());
		assertEquals(bin1.getBinId(), list.get(1).getBinId());
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

		BinSkuData list = binSkuDto.get(pojo.getId());

		assertEquals(product.getName(), list.getProductName());
		assertEquals(bin.getBinId(), list.getBinId());
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
		
		InventoryPojo inv= new InventoryPojo();
		inv.setProduct(product);
		inv.setAllocatedQuantity(Long.valueOf(11));
		inv.setAvailableQuantity(Long.valueOf(11));
		inv.setFulfilledQuantity(Long.valueOf(11));
		
		inventoryService.add(inv);
		

		BinPojo bin1 = binService.add();

		BinSkuPojo pojo1 = new BinSkuPojo();
		pojo1.setBin(bin1);
		pojo1.setProduct(product1);
		pojo1.setQuantity(Long.valueOf(11));

		binSkuService.add(pojo1);
		BinSkuForm form= new BinSkuForm();
		form.setQuantity(Long.valueOf(21));
		binSkuDto.update(pojo.getId(), form);
		BinSkuPojo list = binSkuService.get(pojo.getId());

		assertEquals(Long.valueOf(21), list.getQuantity());

	}

}

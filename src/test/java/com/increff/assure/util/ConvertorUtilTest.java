package com.increff.assure.util;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.increff.assure.model.BinData;
import com.increff.assure.model.BinSkuData;
import com.increff.assure.model.BinSkuForm;
import com.increff.assure.model.ClientData;
import com.increff.assure.model.ClientForm;
import com.increff.assure.model.ProductData;
import com.increff.assure.model.ProductForm;
import com.increff.assure.pojo.BinPojo;
import com.increff.assure.pojo.BinSkuPojo;
import com.increff.assure.pojo.ClientPojo;
import com.increff.assure.pojo.ClientType;
import com.increff.assure.pojo.ProductPojo;

public class ConvertorUtilTest {

	@Test
	public void testConvertClientPojoToClientData() {

		ClientPojo c = new ClientPojo();
		c.setName("assure");
		c.setType(ClientType.CLIENT);
		int value = 1;
		c.setId(Long.valueOf(value));

		ClientData data = ConvertorUtil.convert(c);

		assertEquals("assure", data.getName());
		assertEquals(ClientType.CLIENT, data.getType());

	}

	@Test
	public void testConvertClientFormToClientPojo() {

		ClientForm c = new ClientForm();
		c.setName("assure");
		c.setType(ClientType.CLIENT);
		ClientPojo data = ConvertorUtil.convert(c);
		assertEquals("assure", data.getName());
		assertEquals(ClientType.CLIENT, data.getType());

	}

	@Test
	public void testConvertClientPojoToClientDataList() {

		ClientPojo c = new ClientPojo();
		c.setName("assure");
		c.setType(ClientType.CLIENT);
		int value = 1;
		c.setId(Long.valueOf(value));

		ClientPojo c1 = new ClientPojo();
		c1.setName("spring");
		c1.setType(ClientType.CUSTOMER);
		value = 2;
		c1.setId(Long.valueOf(value));

		List<ClientPojo> data = new ArrayList<ClientPojo>();
		data.add(c);
		data.add(c1);

		List<ClientData> list = ConvertorUtil.convertClients(data);

		assertEquals(2, list.size());
		assertEquals(c.getName(), list.get(0).getName());
		assertEquals(c.getType(), list.get(0).getType());
		assertEquals(c1.getName(), list.get(1).getName());
		assertEquals(c1.getType(), list.get(1).getType());

	}

	@Test
	public void testConvertProductPojoToProductData() {
		ClientPojo client = new ClientPojo();
		client.setName("assure");
		client.setType(ClientType.CLIENT);
		client.setId(Long.valueOf(1));

		ProductPojo c = new ProductPojo();
		c.setName("assure");
		c.setBrandId("brand");
		c.setClientSkuId("clientsku");
		c.setDescription("this is description");
		c.setMrp(1.1);
		c.setClient(client);
		c.setGlobalSkuId(Long.valueOf(1));

		ProductData list = ConvertorUtil.convert(c);
		assertEquals(client.getId(), list.getClientId());
		assertEquals(c.getName(), list.getName());
		assertEquals(c.getMrp(), list.getMrp());
		assertEquals(c.getDescription(), list.getDescription());
		assertEquals(c.getBrandId(), list.getBrandId());
		assertEquals(c.getClientSkuId(), list.getClientSkuId());
		assertEquals(c.getGlobalSkuId(), list.getGlobalSkuId());

	}

	@Test
	public void testConvertProductFormToProductPojo() {
		ProductForm c = new ProductForm();
		c.setName("assure");
		c.setBrandId("brand");
		c.setClientSkuId("clientsku");
		c.setDescription("this is description");
		c.setMrp(1.1);

		ClientPojo client = new ClientPojo();
		client.setName("assure");
		client.setType(ClientType.CLIENT);
		client.setId(Long.valueOf(1));

		ProductPojo list = ConvertorUtil.convert(c, client);
		assertEquals(client.getId(), list.getClient().getId());
		assertEquals(c.getName(), list.getName());
		assertEquals(c.getMrp(), list.getMrp());
		assertEquals(c.getDescription(), list.getDescription());
		assertEquals(c.getBrandId(), list.getBrandId());
		assertEquals(c.getClientSkuId(), list.getClientSkuId());

	}

	@Test
	public void testConvertProductPojoToProductDataList() {
		ClientPojo client = new ClientPojo();
		client.setName("assure");
		client.setType(ClientType.CLIENT);
		client.setId(Long.valueOf(1));

		ProductPojo c = new ProductPojo();
		c.setName("assure");
		c.setBrandId("brand");
		c.setClientSkuId("clientsku");
		c.setDescription("this is description");
		c.setMrp(1.1);
		c.setClient(client);
		c.setGlobalSkuId(Long.valueOf(1));

		List<ProductPojo> pojos = new ArrayList<ProductPojo>();
		pojos.add(c);

		List<ProductData> list = ConvertorUtil.convertProducts(pojos);
		assertEquals(1, list.size());
		assertEquals(client.getId(), list.get(0).getClientId());
		assertEquals(c.getName(), list.get(0).getName());
		assertEquals(c.getMrp(), list.get(0).getMrp());
		assertEquals(c.getDescription(), list.get(0).getDescription());
		assertEquals(c.getBrandId(), list.get(0).getBrandId());
		assertEquals(c.getClientSkuId(), list.get(0).getClientSkuId());
		assertEquals(c.getGlobalSkuId(), list.get(0).getGlobalSkuId());
	}

	@Test
	public void testConvertBinPojoToBinData() {
		BinPojo b = new BinPojo();
		b.setBinId(Long.valueOf(1));

		BinData list = ConvertorUtil.convert(b);
		assertEquals(b.getBinId(), list.getBinId());

	}

	@Test
	public void testConvertBinPojoToBinDataList() {
		BinPojo b = new BinPojo();
		b.setBinId(Long.valueOf(1));

		BinPojo b1 = new BinPojo();
		b1.setBinId(Long.valueOf(2));

		List<BinPojo> pojos = new ArrayList<BinPojo>();
		pojos.add(b);
		pojos.add(b1);

		List<BinData> list = ConvertorUtil.convertBins(pojos);
		assertEquals(2, list.size());
		assertEquals(b.getBinId(), list.get(0).getBinId());
		assertEquals(b1.getBinId(), list.get(1).getBinId());

	}

	@Test
	public void testConvertBinSkuPojoToBinSkuData() {

		ClientPojo client = new ClientPojo();
		client.setName("assure");
		client.setType(ClientType.CLIENT);
		client.setId(Long.valueOf(1));

		ProductPojo c = new ProductPojo();
		c.setName("assure");
		c.setBrandId("brand");
		c.setClientSkuId("clientsku");
		c.setDescription("this is description");
		c.setMrp(1.1);
		c.setClient(client);
		c.setGlobalSkuId(Long.valueOf(1));

		BinPojo bin = new BinPojo();
		bin.setBinId(Long.valueOf(1));

		BinSkuPojo binSku = new BinSkuPojo();
		binSku.setBin(bin);
		binSku.setId(Long.valueOf(1));
		binSku.setProduct(c);
		binSku.setQuantity(Long.valueOf(11));

		BinSkuData list = ConvertorUtil.convert(binSku);

		assertEquals(c.getName(), list.getProductName());
		assertEquals(bin.getBinId(), list.getBinId());
		assertEquals(binSku.getQuantity(), list.getQuantity());

	}

	@Test
	public void testConvertBinSkuFormToBinSkuPojo() {
		ClientPojo client = new ClientPojo();
		client.setName("assure");
		client.setType(ClientType.CLIENT);
		client.setId(Long.valueOf(1));

		ProductPojo c = new ProductPojo();
		c.setName("assure");
		c.setBrandId("brand");
		c.setClientSkuId("clientsku");
		c.setDescription("this is description");
		c.setMrp(1.1);
		c.setClient(client);
		c.setGlobalSkuId(Long.valueOf(1));

		BinPojo bin = new BinPojo();
		bin.setBinId(Long.valueOf(1));

		BinSkuForm form = new BinSkuForm();
		form.setQuantity(Long.valueOf(10));

		BinSkuPojo list = ConvertorUtil.convert(form, c, bin);
		assertEquals(c.getName(), list.getProduct().getName());
		assertEquals(bin.getBinId(), list.getBin().getBinId());
		assertEquals(form.getQuantity(), list.getQuantity());

	}

	@Test
	public void testConvertBinSkuPojoToBinSkuDataList() {

		ClientPojo client = new ClientPojo();
		client.setName("assure");
		client.setType(ClientType.CLIENT);
		client.setId(Long.valueOf(1));

		ProductPojo c = new ProductPojo();
		c.setName("assure");
		c.setBrandId("brand");
		c.setClientSkuId("clientsku");
		c.setDescription("this is description");
		c.setMrp(1.1);
		c.setClient(client);
		c.setGlobalSkuId(Long.valueOf(1));

		BinPojo bin = new BinPojo();
		bin.setBinId(Long.valueOf(1));

		BinSkuPojo binSku = new BinSkuPojo();
		binSku.setBin(bin);
		binSku.setId(Long.valueOf(1));
		binSku.setProduct(c);
		binSku.setQuantity(Long.valueOf(11));

		List<BinSkuPojo> pojos = new ArrayList<BinSkuPojo>();
		pojos.add(binSku);

		List<BinSkuData> list = ConvertorUtil.convertBinSkus(pojos);

		assertEquals(1, list.size());
		assertEquals(c.getName(), list.get(0).getProductName());
		assertEquals(bin.getBinId(), list.get(0).getBinId());
		assertEquals(binSku.getQuantity(), list.get(0).getQuantity());

	}

}

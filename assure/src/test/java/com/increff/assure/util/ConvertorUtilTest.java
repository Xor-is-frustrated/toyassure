package com.increff.assure.util;

import com.increff.assure.pojo.BinPojo;
import com.increff.assure.pojo.BinSkuPojo;
import com.increff.assure.pojo.ClientPojo;
import com.increff.assure.pojo.ProductPojo;
import com.increff.commons.data.BinData;
import com.increff.commons.data.BinSkuData;
import com.increff.commons.data.ClientData;
import com.increff.commons.data.ProductData;
import com.increff.commons.enums.ClientType;
import com.increff.commons.form.BinSkuForm;
import com.increff.commons.form.ClientForm;
import com.increff.commons.form.ProductForm;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ConvertorUtilTest {

	@Test
	public void testConvertClientPojoToClientData() {

		ClientPojo c = new ClientPojo();
		c.setName("assure");
		c.setType(ClientType.CLIENT);
		int value = 1;
		c.setId(Long.valueOf(value));

		ClientData data = ConvertorUtil.convert(c);
		data.setType(ClientType.CLIENT);
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
		assertEquals(ClientType.CLIENT,data.getType());

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
		assertEquals(ClientType.CLIENT,list.get(0).getType());
		assertEquals(c1.getName(), list.get(1).getName());
		assertEquals(ClientType.CUSTOMER,list.get(1).getType());

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
		assertEquals(client.getName(), list.getClientName());
		assertEquals(c.getName(), list.getName());
		assertEquals(c.getMrp(), list.getMrp());
		assertEquals(c.getDescription(), list.getDescription());
		assertEquals(c.getBrandId(), list.getBrandId());
		assertEquals(c.getClientSkuId(), list.getClientSkuId());
		assertEquals(c.getGlobalSkuId(), list.getId());

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
		assertEquals(client.getName(), list.get(0).getClientName());
		assertEquals(c.getName(), list.get(0).getName());
		assertEquals(c.getMrp(), list.get(0).getMrp());
		assertEquals(c.getDescription(), list.get(0).getDescription());
		assertEquals(c.getBrandId(), list.get(0).getBrandId());
		assertEquals(c.getClientSkuId(), list.get(0).getClientSkuId());
		assertEquals(c.getGlobalSkuId(), list.get(0).getId());
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

//	@Test
//	public void testConvertChannelFormToChannelPojo() {
//		ChannelForm form = new ChannelForm();
//		form.setName("assure");
//		form.setType(ChannelType.CHANNEL);
//
//		ChannelPojo list = ConvertorUtil.convert(form);
//		assertEquals(form.getName(), list.getName());
//		assertEquals(form.getType(), list.getType());
//	}
//
//	@Test
//	public void testConvertChannelPojoToChannelData() {
//		ChannelPojo form = new ChannelPojo();
//		form.setName("assure");
//		form.setType(ChannelType.CHANNEL);
//		form.setId(Long.valueOf(1));
//
//		ChannelData list = ConvertorUtil.convert(form);
//		assertEquals(form.getName(), list.getName());
//		assertEquals(form.getType(), list.getType());
//		assertEquals(form.getId(), list.getId());
//
//	}
//
//	@Test
//	public void testConvertChannelPojoToChannelDataList() {
//		ChannelPojo form = new ChannelPojo();
//		form.setName("assure");
//		form.setType(ChannelType.CHANNEL);
//		form.setId(Long.valueOf(1));
//
//		ChannelPojo form1 = new ChannelPojo();
//		form1.setName("assure");
//		form1.setType(ChannelType.CHANNEL);
//		form1.setId(Long.valueOf(2));
//
//		List<ChannelPojo> pojos = new ArrayList<ChannelPojo>();
//		pojos.add(form);
//		pojos.add(form1);
//
//		List<ChannelData> list = ConvertorUtil.convertChannels(pojos);
//
//		assertEquals(2, list.size());
//		assertEquals(form.getName(), list.get(0).getName());
//		assertEquals(form.getType(), list.get(0).getType());
//		assertEquals(form.getId(), list.get(0).getId());
//
//		assertEquals(form1.getName(), list.get(1).getName());
//		assertEquals(form1.getType(), list.get(1).getType());
//		assertEquals(form1.getId(), list.get(1).getId());
//
//	}
//
//	@Test
//	public void testConvertChannelListingPojoToData() {
//		ClientPojo client = new ClientPojo();
//		client.setName("assure");
//		client.setType(ClientType.CLIENT);
//		client.setId(Long.valueOf(1));
//
//		ProductPojo c = new ProductPojo();
//		c.setName("assure");
//		c.setBrandId("brand");
//		c.setClientSkuId("clientsku");
//		c.setDescription("this is description");
//		c.setMrp(1.1);
//		c.setClient(client);
//		c.setGlobalSkuId(Long.valueOf(1));
//
//		ChannelPojo channel = new ChannelPojo();
//		channel.setName("assure");
//		channel.setType(ChannelType.CHANNEL);
//		channel.setId(Long.valueOf(1));
//
//		ChannelListingPojo cl = new ChannelListingPojo();
//		cl.setChannel(channel);
//		cl.setChannelSkuId("channelsku");
//		cl.setClient(client);
//		cl.setId(Long.valueOf(1));
//		cl.setProduct(c);
//
//		ChannelListingData list = ConvertorUtil.convert(cl);
//		assertEquals(cl.getChannelSkuId(), list.getChannelSkuId());
//		assertEquals(cl.getChannel().getName(), list.getChannelName());
//		assertEquals(cl.getClient().getName(), list.getClientName());
//		assertEquals(cl.getProduct().getClientSkuId(), list.getClientSkuId());
//
//	}
//
//	@Test
//	public void testConvertChannelListingFormToPojo() {
//		ClientPojo client = new ClientPojo();
//		client.setName("assure");
//		client.setType(ClientType.CLIENT);
//		client.setId(Long.valueOf(1));
//
//		ProductPojo c = new ProductPojo();
//		c.setName("assure");
//		c.setBrandId("brand");
//		c.setClientSkuId("clientsku");
//		c.setDescription("this is description");
//		c.setMrp(1.1);
//		c.setClient(client);
//		c.setGlobalSkuId(Long.valueOf(1));
//
//		ChannelPojo channel = new ChannelPojo();
//		channel.setName("assure");
//		channel.setType(ChannelType.CHANNEL);
//		channel.setId(Long.valueOf(1));
//
//		ChannelListingForm form = new ChannelListingForm();
//		form.setClientSkuId("clientsku");
//		form.setChannelSkuId("channelsku");
//		form.setChannelName("assure");
//		form.setClientName("assure");
//
//		ChannelListingPojo list = ConvertorUtil.convert(form, client, c, channel);
//		assertEquals(form.getChannelSkuId(), list.getChannelSkuId());
//		assertEquals(form.getChannelName(), list.getChannel().getName());
//		assertEquals(form.getClientName(), list.getClient().getName());
//		assertEquals(form.getClientSkuId(), list.getProduct().getClientSkuId());
//
//	}

}

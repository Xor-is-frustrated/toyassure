package com.increff.assure.util;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.increff.assure.model.ClientData;
import com.increff.assure.model.ClientForm;
import com.increff.assure.pojo.ClientPojo;
import com.increff.assure.pojo.ClientType;

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

}

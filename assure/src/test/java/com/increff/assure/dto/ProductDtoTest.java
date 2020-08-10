package com.increff.assure.dto;

import static org.junit.Assert.assertEquals;

import java.util.List;

import com.increff.commons.data.ProductData;
import com.increff.commons.form.ProductForm;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.increff.assure.pojo.ClientPojo;
import com.increff.commons.enums.ClientType;
import com.increff.assure.pojo.ProductPojo;
import com.increff.assure.service.AbstractUnitTest;
import com.increff.assure.service.ApiException;
import com.increff.assure.service.ClientService;
import com.increff.assure.service.ProductService;

public class ProductDtoTest extends AbstractUnitTest {

	@Autowired
	private ClientService clientService;

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductDto productDto;

	@Test
	public void testAdd() throws ApiException {
		ClientPojo client = new ClientPojo();
		client.setName("assure");
		client.setType(ClientType.CLIENT);

		clientService.add(client);

		ProductForm c = new ProductForm();
		c.setName("assure");
		c.setBrandId("brand");
		c.setClientSkuId("clientsku");
		c.setDescription("this is description");
		c.setMrp(1.1);
		c.setClientName(client.getName());

		productDto.add(c);
	}

	@Test
	public void testById() throws ApiException {
		ClientPojo client = new ClientPojo();
		client.setName("assure");
		client.setType(ClientType.CLIENT);

		clientService.add(client);

		ProductPojo c = new ProductPojo();
		c.setName("assure");
		c.setBrandId("brand");
		c.setClientSkuId("clientsku");
		c.setDescription("this is description");
		c.setMrp(1.1);
		c.setClient(client);

		productService.add(c);

		ClientPojo client1 = new ClientPojo();
		client1.setName("assure1");
		client1.setType(ClientType.CLIENT);

		clientService.add(client1);

		ProductPojo c1 = new ProductPojo();
		c1.setName("assure");
		c1.setBrandId("brand");
		c1.setClientSkuId("clientsku");
		c1.setDescription("this is description");
		c1.setMrp(1.1);
		c1.setClient(client1);

		productService.add(c1);

		ProductData list = productDto.get(c.getGlobalSkuId());

		assertEquals(client.getName(), list.getClientName());
		assertEquals(c.getName(), list.getName());
		assertEquals(c.getMrp(), list.getMrp());
		assertEquals(c.getDescription(), list.getDescription());
		assertEquals(c.getBrandId(), list.getBrandId());
		assertEquals(c.getClientSkuId(), list.getClientSkuId());
		assertEquals(c.getGlobalSkuId(), list.getId());

	}

	@Test
	public void testSelectAll() throws ApiException {
		ClientPojo client = new ClientPojo();
		client.setName("assure");
		client.setType(ClientType.CLIENT);

		clientService.add(client);

		ProductPojo c = new ProductPojo();
		c.setName("assure");
		c.setBrandId("brand");
		c.setClientSkuId("clientsku");
		c.setDescription("this is description");
		c.setMrp(1.1);
		c.setClient(client);

		productService.add(c);

		ClientPojo client1 = new ClientPojo();
		client1.setName("assure1");
		client1.setType(ClientType.CLIENT);

		clientService.add(client1);

		ProductPojo c1 = new ProductPojo();
		c1.setName("assure");
		c1.setBrandId("brand");
		c1.setClientSkuId("clientsku");
		c1.setDescription("this is description");
		c1.setMrp(1.1);
		c1.setClient(client1);

		productService.add(c1);

		List<ProductData> list = productDto.getAll();

		assertEquals(2, list.size());

		assertEquals(client.getName(), list.get(0).getClientName());
		assertEquals(c.getName(), list.get(0).getName());
		assertEquals(c.getMrp(), list.get(0).getMrp());
		assertEquals(c.getDescription(), list.get(0).getDescription());
		assertEquals(c.getBrandId(), list.get(0).getBrandId());
		assertEquals(c.getClientSkuId(), list.get(0).getClientSkuId());
		assertEquals(c.getGlobalSkuId(), list.get(0).getId());

		assertEquals(client1.getName(), list.get(1).getClientName());
		assertEquals(c1.getName(), list.get(1).getName());
		assertEquals(c1.getMrp(), list.get(1).getMrp());
		assertEquals(c1.getDescription(), list.get(1).getDescription());
		assertEquals(c1.getBrandId(), list.get(1).getBrandId());
		assertEquals(c1.getClientSkuId(), list.get(1).getClientSkuId());
		assertEquals(c1.getGlobalSkuId(), list.get(1).getId());

	}
	
	@Test
	public void testUpdate() throws ApiException {
		ClientPojo client = new ClientPojo();
		client.setName("assure");
		client.setType(ClientType.CLIENT);

		clientService.add(client);

		ProductPojo c = new ProductPojo();
		c.setName("assure");
		c.setBrandId("brand");
		c.setClientSkuId("clientsku");
		c.setDescription("this is description");
		c.setMrp(1.1);
		c.setClient(client);

		productService.add(c);
		
		ProductForm form = new ProductForm();
		form.setName("assure change");
		form.setBrandId("brand change");
		form.setClientSkuId("clientsku");
		form.setDescription("description change");
		form.setClientName(client.getName());
		form.setMrp(1.1);
		

		productDto.update(c.getGlobalSkuId(), form);
		ProductPojo pojo = productService.get(c.getGlobalSkuId());
		assertEquals("brand change", pojo.getBrandId());
		assertEquals("assure change", pojo.getName());
		assertEquals("description change", pojo.getDescription());

	}

}

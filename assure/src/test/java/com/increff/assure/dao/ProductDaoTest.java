package com.increff.assure.dao;

import com.increff.assure.pojo.PartyPojo;
import com.increff.assure.pojo.ProductPojo;
import com.increff.assure.service.AbstractUnitTest;
import com.increff.commons.enums.PartyType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProductDaoTest extends AbstractUnitTest {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private PartyDao partyDao;

    @Test
    public void testInsert() {
        PartyPojo client = new PartyPojo();
        client.setName("assure");
        client.setType(PartyType.CLIENT);
        partyDao.insert(client);
        ProductPojo c = new ProductPojo();
        c.setName("assure");
        c.setBrandId("brand");
        c.setClientSkuId("clientsku");
        c.setDescription("this is description");
        c.setMrp(1.1);
        c.setParty(client);
        productDao.insert(c);

    }

    @Test
    public void testSelectAll() {
        PartyPojo client = new PartyPojo();
        client.setName("assure");
        client.setType(PartyType.CLIENT);
        partyDao.insert(client);
        ProductPojo c = new ProductPojo();
        c.setName("assure");
        c.setBrandId("brand");
        c.setClientSkuId("clientsku");
        c.setDescription("this is description");
        c.setMrp(1.1);
        c.setParty(client);
        productDao.insert(c);
        PartyPojo client1 = new PartyPojo();
        client1.setName("assure1");
        client1.setType(PartyType.CLIENT);
        partyDao.insert(client1);
        ProductPojo c1 = new ProductPojo();
        c1.setName("assure");
        c1.setBrandId("brand");
        c1.setClientSkuId("clientsku");
        c1.setDescription("this is description");
        c1.setMrp(1.1);
        c1.setParty(client1);
        productDao.insert(c1);
        List<ProductPojo> list = productDao.selectByClientName("assure");
        assertEquals(1, list.size());
        assertEquals(client, list.get(0).getParty());
        assertEquals(c.getName(), list.get(0).getName());
        assertEquals(c.getMrp(), list.get(0).getMrp());
        assertEquals(c.getDescription(), list.get(0).getDescription());
        assertEquals(c.getBrandId(), list.get(0).getBrandId());
        assertEquals(c.getClientSkuId(), list.get(0).getClientSkuId());
        assertEquals(c.getGlobalSkuId(), list.get(0).getGlobalSkuId());
//        assertEquals(client1, list.get(1).getParty());
//        assertEquals(c1.getName(), list.get(1).getName());
//        assertEquals(c1.getMrp(), list.get(1).getMrp());
//        assertEquals(c1.getDescription(), list.get(1).getDescription());
//        assertEquals(c1.getBrandId(), list.get(1).getBrandId());
//        assertEquals(c1.getClientSkuId(), list.get(1).getClientSkuId());
//        assertEquals(c1.getGlobalSkuId(), list.get(1).getGlobalSkuId());

    }

    @Test
    public void testById() {
        PartyPojo client = new PartyPojo();
        client.setName("assure");
        client.setType(PartyType.CLIENT);
        partyDao.insert(client);
        ProductPojo c = new ProductPojo();
        c.setName("assure");
        c.setBrandId("brand");
        c.setClientSkuId("clientsku");
        c.setDescription("this is description");
        c.setMrp(1.1);
        c.setParty(client);
        productDao.insert(c);
        PartyPojo client1 = new PartyPojo();
        client1.setName("assure1");
        client1.setType(PartyType.CLIENT);
        partyDao.insert(client1);
        ProductPojo c1 = new ProductPojo();
        c1.setName("assure");
        c1.setBrandId("brand");
        c1.setClientSkuId("clientsku");
        c1.setDescription("this is description");
        c1.setMrp(1.1);
        c1.setParty(client1);
        productDao.insert(c1);
        ProductPojo list = productDao.select(c.getGlobalSkuId());
        assertEquals(client, list.getParty());
        assertEquals(c.getName(), list.getName());
        assertEquals(c.getMrp(), list.getMrp());
        assertEquals(c.getDescription(), list.getDescription());
        assertEquals(c.getBrandId(), list.getBrandId());
        assertEquals(c.getClientSkuId(), list.getClientSkuId());
        assertEquals(c.getGlobalSkuId(), list.getGlobalSkuId());

    }

    @Test
    public void testSelectByClientSkuIdAndClient() {
        PartyPojo client = new PartyPojo();
        client.setName("assure");
        client.setType(PartyType.CLIENT);
        partyDao.insert(client);
        ProductPojo c = new ProductPojo();
        c.setName("assure");
        c.setBrandId("brand");
        c.setClientSkuId("clientsku");
        c.setDescription("this is description");
        c.setMrp(1.1);
        c.setParty(client);
        productDao.insert(c);
        PartyPojo client1 = new PartyPojo();
        client1.setName("assure1");
        client1.setType(PartyType.CLIENT);
        partyDao.insert(client1);
        ProductPojo c1 = new ProductPojo();
        c1.setName("assure");
        c1.setBrandId("brand");
        c1.setClientSkuId("clientsku");
        c1.setDescription("this is description");
        c1.setMrp(1.1);
        c1.setParty(client1);
        productDao.insert(c1);
        ProductPojo list = productDao.selectByClientIdAndClientSkuId("clientsku", client.getId());
        assertEquals(client, list.getParty());
        assertEquals(c.getName(), list.getName());
        assertEquals(c.getMrp(), list.getMrp());
        assertEquals(c.getDescription(), list.getDescription());
        assertEquals(c.getBrandId(), list.getBrandId());
        assertEquals(c.getClientSkuId(), list.getClientSkuId());
        assertEquals(c.getGlobalSkuId(), list.getGlobalSkuId());

    }

}

package com.increff.assure.dao;

import com.increff.assure.pojo.PartyPojo;
import com.increff.assure.service.AbstractUnitTest;
import com.increff.commons.enums.PartyType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class PartyDaoTest extends AbstractUnitTest {

    @Autowired
    private PartyDao dao;

    @Test
    public void testInsert() {
        PartyPojo c = new PartyPojo();
        c.setName("assure");
        c.setType(PartyType.CLIENT);
        dao.insert(c);

    }

    @Test
    public void testSelectAll() {
        PartyPojo c = new PartyPojo();
        c.setName("assure");
        c.setType(PartyType.CLIENT);
        dao.insert(c);
        PartyPojo c1 = new PartyPojo();
        c1.setName("spring");
        c1.setType(PartyType.CUSTOMER);
        dao.insert(c1);
        List<PartyPojo> list = dao.selectAllClients();
        assertEquals(1, list.size());
        assertEquals(c.getName(), list.get(0).getName());
        assertEquals(c.getType(), list.get(0).getType());
//        assertEquals(c1.getName(), list.get(1).getName());
//        assertEquals(c1.getType(), list.get(1).getType());
    }

    @Test
    public void testById() {
        PartyPojo c = new PartyPojo();
        c.setName("assure");
        c.setType(PartyType.CLIENT);
        dao.insert(c);
        PartyPojo c1 = new PartyPojo();
        c1.setName("spring");
        c1.setType(PartyType.CUSTOMER);
        dao.insert(c1);
        PartyPojo list = dao.select(c.getId());
        assertEquals(c.getName(), list.getName());
        assertEquals(c.getType(), list.getType());

    }

    @Test
    public void testByName() {
        PartyPojo c = new PartyPojo();
        c.setName("assure");
        c.setType(PartyType.CLIENT);
        dao.insert(c);
        PartyPojo c1 = new PartyPojo();
        c1.setName("spring");
        c1.setType(PartyType.CUSTOMER);
        dao.insert(c1);
        PartyPojo list = dao.selectByName(c.getName());
        assertEquals(c.getName(), list.getName());
        assertEquals(c.getType(), list.getType());

    }

}

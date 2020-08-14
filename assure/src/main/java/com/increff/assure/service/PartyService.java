package com.increff.assure.service;

import com.increff.assure.dao.PartyDao;
import com.increff.assure.pojo.PartyPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class PartyService extends AbstractService {


    @Autowired
    private PartyDao partyDao;

    @Transactional(rollbackFor = ApiException.class)
    public PartyPojo add(PartyPojo p) throws ApiException {

        checkZero(p.getName().length(), "Name cannot be empty.");
        PartyPojo existing = partyDao.selectByName(p.getName());
        checkNull(existing, "Name already exists: " + p.getName());

        PartyPojo partyPojo = partyDao.insert(p);
        return partyPojo;
    }

    @Transactional(readOnly = true)
    public PartyPojo get(Long id) throws ApiException {
        PartyPojo p = partyDao.select(id);
        checkNotNull(p, "Client ID does not exist: " + id);
        return p;
    }

    @Transactional(readOnly = true)
    public PartyPojo getByName(String name) throws ApiException {
        PartyPojo p = partyDao.selectByName(name);
        checkNotNull(p, "Client Name does not exist: " + name);

        return p;
    }


    @Transactional(readOnly = true)
    public List<PartyPojo> getAll() {
        return partyDao.selectAll();
    }

    @Transactional(readOnly = true)
    public List<PartyPojo> getAllClients() {
        return partyDao.selectAllClients();
    }

    @Transactional(readOnly = true)
    public List<PartyPojo> getAllCustomers() {
        return partyDao.selectAllCustomers();
    }

    @Transactional(rollbackFor = ApiException.class)
    public PartyPojo update(Long id, String name) throws ApiException {
        checkZero(name.length(), "Name cannot be empty.");

        PartyPojo ex = partyDao.selectByName(name);
        if (ex != null && ex.getId() != id) {
            throw new ApiException("Name already exists: " + name);
        }

        PartyPojo p = partyDao.select(id);
        checkNotNull(p, "Client ID does not exist: " + id);
        p.setName(name);
        return p;
    }

}

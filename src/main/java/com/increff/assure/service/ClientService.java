package com.increff.assure.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.increff.assure.dao.ClientDao;
import com.increff.assure.pojo.ClientPojo;


@Service
public class ClientService extends AbstractService{

	
	@Autowired
	private ClientDao dao;
	
	@Transactional(rollbackFor = ApiException.class)
	public ClientPojo add(ClientPojo p) throws ApiException {

	
		checkZero(p.getName().length(),"Name cannot be empty.");
		ClientPojo existing= dao.selectByName(p.getName());
		checkNull(existing,"Name already exists.");

		ClientPojo clientPojo = dao.insert(p);
		return clientPojo;
	}
	
	@Transactional(readOnly = true)
	public ClientPojo get(Long id) throws ApiException {
		ClientPojo p = dao.select(id);
		checkNotNull(p, "Client ID does not exist");
		return p;
	}
	
	@Transactional(readOnly = true)
	public ClientPojo getByName(String name) throws ApiException {
		ClientPojo p = dao.selectByName(name);
		checkNotNull(p, "Client Name does not exist");
		return p;
	}

	
	@Transactional(readOnly = true)
	public List<ClientPojo> getAll() {
		return dao.selectAll();
	}
	
	@Transactional(rollbackFor = ApiException.class)
	public ClientPojo update(Long id, ClientPojo pojo) throws ApiException {
		checkZero(pojo.getName().length(),"Name cannot be empty.");
		
		ClientPojo ex=dao.selectByName(pojo.getName());
		if(ex!=null && ex.getId()!=id) {
			throw new ApiException("Name already exists");
		}
		  
		ClientPojo p = dao.select(id);
		checkNotNull(p, "Client ID does not exist");
		p.setName(pojo.getName());
		return p;
	}
	
}

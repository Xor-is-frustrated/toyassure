package com.increff.assure.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.increff.assure.dao.BinDao;
import com.increff.assure.pojo.BinPojo;

@Service
public class BinService extends AbstractService{
	
	@Autowired
	private BinDao dao;
	
	@Transactional(rollbackFor = ApiException.class)
	public BinPojo add() throws ApiException {
		BinPojo p=new BinPojo();
		BinPojo binPojo = dao.insert(p);
		return binPojo;
	}
	
	@Transactional(readOnly = true)
	public BinPojo get(Long id) throws ApiException {
		BinPojo p = dao.select(id);
		checkNotNull(p, "Bin ID does not exist");
		return p;
	}
	
	@Transactional(readOnly = true)
	public List<BinPojo> getAll() {
		return dao.selectAll();
	}
}

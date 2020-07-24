package com.increff.assure.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.increff.assure.dao.ClientDao;
import com.increff.assure.dao.ProductDao;
import com.increff.assure.pojo.ClientPojo;
import com.increff.assure.pojo.ProductPojo;

@Service
public class ProductService extends AbstractService {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private ClientDao clientDao;

	@Transactional(rollbackFor = ApiException.class)
	public ProductPojo add(ProductPojo p) throws ApiException {

		checkZero(p.getName().length(), "Name cannot be empty.");
		checkZero(p.getClientSkuId().length(), "ClientSkuId cannot be empty.");
		checkZero(p.getBrandId().length(), "BrandId cannot be empty.");
		checkPositive(p.getMrp(), "Mrp cannot be less than zero");

		ProductPojo pojo = productDao.selectByClientIdAndClientSkuId(p.getClientSkuId(), p.getClient());
		checkNull(pojo, "Client and ClientSkuId combination already exists");

		ClientPojo client = clientDao.select(p.getClient().getId());
		checkNotNull(client, "Client Id does not exist");

		ProductPojo productPojo = productDao.insert(p);
		return productPojo;
	}

	@Transactional(readOnly = true)
	public ProductPojo get(Long id) throws ApiException {
		ProductPojo p = productDao.select(id);
		checkNotNull(p, "GlobalSkuID does not exist");
		return p;
	}

// This function is not necessary for now..	
//	@Transactional(readOnly = true)
//	public ProductPojo getByClientIdAndClientSkuId(String clientSkuId, ClientPojo client) throws ApiException {
//		ClientPojo p = clientDao.select(client.getId());
//		checkNotNull(p, "Client ID does not exist");
//		
//		ProductPojo pojo = productDao.selectByClientIdAndClientSkuId(clientSkuId, client);
//		checkNotNull(pojo, "ClientSkuId and Client combination does not exist");
//		
//		return pojo;
//	}

	@Transactional(readOnly = true)
	public List<ProductPojo> getAll() {
		return productDao.selectAll();
	}

}

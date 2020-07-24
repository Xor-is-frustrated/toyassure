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

		ProductPojo pojo = productDao.selectByClientAndClientSkuId(p.getClientSkuId(), p.getClient());
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
	
	@Transactional(readOnly = true)
	public ProductPojo getByClientIdAndClientSkuId(String clientSkuId, ClientPojo client) throws ApiException {
		ClientPojo p = clientDao.select(client.getId());
		checkNotNull(p, "Client ID does not exist");
		
		ProductPojo pojo = productDao.selectByClientAndClientSkuId(clientSkuId, client);
		checkNotNull(pojo, "ClientSkuId and Client combination does not exist");
		
		return pojo;
	}

	@Transactional(readOnly = true)
	public List<ProductPojo> getAll() {
		return productDao.selectAll();
	}

	@Transactional(rollbackFor = ApiException.class)
	public void update(Long id, ProductPojo p) throws ApiException {

		ProductPojo existing = productDao.selectByClientAndClientSkuId(p.getClientSkuId(), p.getClient());
		if (existing != null && existing.getGlobalSkuId() != id) {
			throw new ApiException("Client and ClientSkuId combination already exists");
		}

		checkZero(p.getName().length(), "Name cannot be empty.");
		checkZero(p.getClientSkuId().length(), "ClientSkuId cannot be empty.");
		checkZero(p.getBrandId().length(), "BrandId cannot be empty.");
		checkPositive(p.getMrp(), "Mrp cannot be less than zero");

		ClientPojo client = clientDao.select(p.getClient().getId());
		checkNotNull(client, "Client Id does not exist");

		ProductPojo ex = productDao.select(id);
		checkNotNull(ex, "Product ID does not exist");

		ex.setName(p.getName());
		ex.setMrp(p.getMrp());
		ex.setBrandId(p.getBrandId());
		ex.setDescription(p.getDescription());
	}

}

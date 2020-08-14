package com.increff.assure.service;

import java.util.List;

import com.increff.assure.dao.ProductDao;
import com.increff.assure.pojo.PartyPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.increff.assure.dao.PartyDao;
import com.increff.commons.enums.PartyType;
import com.increff.assure.pojo.ProductPojo;

@Service
public class ProductService extends AbstractService {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private PartyDao partyDao;

	@Transactional(rollbackFor = ApiException.class)
	public ProductPojo add(ProductPojo p) throws ApiException {

		checkZero(p.getName().length(), "Name cannot be empty.");
		checkZero(p.getClientSkuId().length(), "ClientSkuId cannot be empty.");
		checkZero(p.getBrandId().length(), "BrandId cannot be empty.");
		checkMRP(p.getMrp(), "Mrp must be positive");

		PartyPojo client = partyDao.select(p.getParty().getId());
		checkNotNull(client, "Client does not exist: "+p.getParty().getName());

		if(client.getType()!=PartyType.CLIENT) {
			throw new ApiException("Not a client: "+client.getName());
		}

		ProductPojo pojo = productDao.selectByClientIdAndClientSkuId(p.getClientSkuId(), p.getParty().getId());
		checkNull(pojo, "Client and ClientSkuId combination already exists. Client: "+client.getName()+", ClientSku: "+p.getClientSkuId());

		ProductPojo productPojo = productDao.insert(p);
		return productPojo;
	}

	@Transactional(readOnly = true)
	public ProductPojo get(Long globalSkuId) throws ApiException {
		ProductPojo p = productDao.select(globalSkuId);
		checkNotNull(p, "GlobalSkuID does not exist: "+globalSkuId);
		return p;
	}
	
	@Transactional(readOnly = true)
	public ProductPojo getByClientIdAndClientSkuId(String clientSkuId, Long clientId) throws ApiException {
		PartyPojo p = partyDao.select(clientId);
		checkNotNull(p, "Client does not exist");
		if(p.getType()!=PartyType.CLIENT) {
			throw new ApiException("Not a client: "+p.getName());
		}
		ProductPojo pojo = productDao.selectByClientIdAndClientSkuId(clientSkuId, clientId);
		checkNotNull(pojo, "ClientSkuId and Client combination does not exist. Client Id: "+clientId+", " +
				"ClientSku: "+clientSkuId);
		
		return pojo;
	}

	@Transactional(readOnly = true)
	public List<ProductPojo> getAll() {
		return productDao.selectAll();
	}

	@Transactional(readOnly = true)
	public List<ProductPojo> getByClientName(String name) {
		return productDao.selectByClientName(name);
	}

	@Transactional(rollbackFor = ApiException.class)
	public void update(Long globalSkuId, String name, String description, double mrp, String brandId) throws ApiException {

		checkZero(name.length(), "Name cannot be empty.");
		checkZero(brandId.length(), "BrandId cannot be empty.");
		checkMRP(mrp, "Mrp must be positive");

		ProductPojo ex = productDao.select(globalSkuId);
		checkNotNull(ex, "Global Sku Id does not exist: "+globalSkuId);

		ex.setName(name);
		ex.setMrp(mrp);
		ex.setBrandId(brandId);
		ex.setDescription(description);
	}

}

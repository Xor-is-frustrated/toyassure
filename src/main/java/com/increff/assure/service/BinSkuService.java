package com.increff.assure.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.increff.assure.dao.BinDao;
import com.increff.assure.dao.BinSkuDao;
import com.increff.assure.dao.ProductDao;
import com.increff.assure.pojo.BinPojo;
import com.increff.assure.pojo.BinSkuPojo;
import com.increff.assure.pojo.ProductPojo;

@Service
public class BinSkuService extends AbstractService {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private BinSkuDao binSkudDao;

	@Autowired
	private BinDao binDao;

	@Transactional(rollbackFor = ApiException.class)
	public BinSkuPojo add(BinSkuPojo p) throws ApiException {
		BinPojo pojo = binDao.select(p.getBin().getBinId());
		checkNotNull(pojo, "Bin Id does not exist");

		ProductPojo product = productDao.select(p.getProduct().getGlobalSkuId());
		checkNotNull(product, "Product Id does not exist");

		checkPositive(p.getQuantity(), "Mrp cannot be less than zero");

		BinSkuPojo bin = binSkudDao.selectByBinAndProduct(p.getBin(), p.getProduct());
		if (bin != null) {
			return updateQuantity(bin.getId(), bin.getQuantity() + p.getQuantity());
		} else {
			return binSkudDao.insert(p);
		}

	}

	@Transactional(readOnly = true)
	public BinSkuPojo get(Long id) throws ApiException {
		BinSkuPojo p = binSkudDao.select(id);
		checkNotNull(p, "BinSku ID does not exist");
		return p;
	}

	@Transactional(readOnly = true)
	public List<BinSkuPojo> getAll() {
		return binSkudDao.selectAll();
	}

	@Transactional(rollbackFor = ApiException.class)
	public BinSkuPojo updateQuantity(Long id, Long quantity) throws ApiException {
		checkPositive(quantity, "Quantity cannot be less than zero");
		BinSkuPojo ex = binSkudDao.select(id);
		checkNotNull(ex, "BinSku ID does not exist");

		ex.setQuantity(quantity);
		return ex;
	}

}

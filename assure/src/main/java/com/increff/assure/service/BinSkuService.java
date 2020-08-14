package com.increff.assure.service;

import java.util.List;

import com.increff.assure.dao.BinDao;
import com.increff.assure.dao.BinSkuDao;
import com.increff.assure.dao.ProductDao;
import com.increff.assure.pojo.BinPojo;
import com.increff.assure.pojo.BinSkuPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.increff.assure.pojo.ProductPojo;

@Service
public class BinSkuService extends AbstractService {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private BinSkuDao binSkuDao;

	@Autowired
	private BinDao binDao;

	@Transactional(rollbackFor = ApiException.class)
	public BinSkuPojo add(BinSkuPojo p) throws ApiException {
		BinPojo pojo = binDao.select(p.getBin().getBinId());
		checkNotNull(pojo, "Bin does not exist. Id: "+p.getBin().getBinId());

		ProductPojo product = productDao.select(p.getProduct().getGlobalSkuId());
		checkNotNull(product, "Global Sku Id does not exist: "+p.getProduct().getGlobalSkuId());

		checkPositive(p.getQuantity(), "Quantity cannot be less than zero. Given value "+p.getQuantity());

		BinSkuPojo bin = binSkuDao.selectByBinIdAndGlobalSkuId(p.getBin().getBinId(), p.getProduct().getGlobalSkuId());
		if (bin != null) {
			return updateQuantity(bin.getId(), bin.getQuantity() + p.getQuantity());
		} else {
			return binSkuDao.insert(p);
		}

	}

	@Transactional(readOnly = true)
	public BinSkuPojo get(Long id) throws ApiException {
		BinSkuPojo p = binSkuDao.select(id);
		checkNotNull(p, "BinSku does not exist. Id: "+id);
		return p;
	}

	@Transactional(readOnly = true)
	public List<BinSkuPojo> getAll() {
		return binSkuDao.selectAll();
	}

	@Transactional(readOnly = true)
	public List<BinSkuPojo> getByProduct(Long globalSkuId) throws ApiException {
		ProductPojo product = productDao.select(globalSkuId);
		checkNotNull(product, "Global Sku Id does not exist. Id: "+globalSkuId);
		return binSkuDao.selectByGlobalSkuId(globalSkuId);
	}

	@Transactional(rollbackFor = ApiException.class)
	public BinSkuPojo updateQuantity(Long id, Long quantity) throws ApiException {
		checkPositive(quantity, "Quantity cannot be less than zero. Given value "+quantity);
		BinSkuPojo ex = binSkuDao.select(id);
		checkNotNull(ex, "BinSku ID does not exist. Id: "+id);

		ex.setQuantity(quantity);
		return ex;
	}

	@Transactional(rollbackFor = ApiException.class)
	public void reduceBinSkuByAllocatedQuantity(Long globalSkuId, Long quantity) throws ApiException {
		List<BinSkuPojo> binSkus = getByProduct(globalSkuId);

		// for the available binSkus reduce the quantity.
		for (BinSkuPojo binSku : binSkus) {

			Long reducedQuantity = Math.min(binSku.getQuantity(), quantity);
			quantity -= reducedQuantity;
			Long remainingQuantity = binSku.getQuantity() - reducedQuantity;
			updateQuantity(binSku.getId(), remainingQuantity);
			if (quantity == Long.valueOf(0)) {
				break;
			}

		}
	}

}

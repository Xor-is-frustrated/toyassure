package com.increff.assure.service;

import com.increff.assure.dao.InventoryDao;
import com.increff.assure.dao.ProductDao;
import com.increff.assure.pojo.InventoryPojo;
import com.increff.assure.pojo.ProductPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryService extends AbstractService {

    @Autowired
    private InventoryDao dao;

    @Autowired
    private ProductDao productDao;

    @Transactional(rollbackFor = ApiException.class)
    public InventoryPojo add(InventoryPojo p) throws ApiException {

        ProductPojo pojo = productDao.select(p.getProduct().getGlobalSkuId());
        checkNotNull(pojo, "GlobalSkuId does not exist: " + p.getProduct().getGlobalSkuId());

        Long availableQuantity = p.getAvailableQuantity();
        checkPositive(availableQuantity, "Available quantity cannot be less than zero. Given value: " + p.getAvailableQuantity());

        InventoryPojo inv = dao.selectByGlobalSkuId(pojo.getGlobalSkuId());
        if (inv != null) {
            availableQuantity += inv.getAvailableQuantity();
            return updateQuantities(inv.getProduct().getGlobalSkuId(), availableQuantity, inv.getAllocatedQuantity(),
                    inv.getFulfilledQuantity());
        } else {
            return dao.insert(p);
        }
    }


    @Transactional(readOnly = true)
    public InventoryPojo getByGlobalSkuId(Long globalSkuId) throws ApiException {

        ProductPojo product = productDao.select(globalSkuId);
        checkNotNull(product, "Global Sku ID does not exist: " + globalSkuId);

        InventoryPojo inv = dao.selectByGlobalSkuId(globalSkuId);
        return inv;
    }

    @Transactional(readOnly = true)
    public List<InventoryPojo> getAll() {
        return dao.selectAll();
    }

    @Transactional(readOnly = true)
    public List<InventoryPojo> getByClientName(String name) {
        return dao.selectByClientName(name);
    }

    @Transactional(rollbackFor = ApiException.class)
    public InventoryPojo updateQuantities(Long globalSkuId, Long availableQuantity, Long allocatedQuantity, Long fulfilledQuantity) throws ApiException {

        InventoryPojo p = dao.selectByGlobalSkuId(globalSkuId);
        checkNotNull(p, "Global Sku ID does not exist: " + globalSkuId);

        checkPositive(availableQuantity, "Available quantity cannot be less than zero. Given: " + availableQuantity);
        checkPositive(allocatedQuantity, "Allocated quantity cannot be less than zero. Given: " + allocatedQuantity);
        checkPositive(fulfilledQuantity, "Fulfilled quantity cannot be less than zero. Given: " + fulfilledQuantity);

        p.setAllocatedQuantity(allocatedQuantity);
        p.setAvailableQuantity(availableQuantity);
        p.setFulfilledQuantity(fulfilledQuantity);
        return p;
    }


}

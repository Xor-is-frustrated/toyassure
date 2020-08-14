package com.increff.assure.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.increff.assure.pojo.InventoryPojo;
import org.springframework.stereotype.Repository;

@Repository
public class InventoryDao extends AbstractDao { 


	private static String selectByGlobalSkuId = "select p from InventoryPojo p where p.product.globalSkuId=:globalSkuId";
	private static String selectAll = "select p from InventoryPojo p";
	private static String selectByClientName = "select p from InventoryPojo p where p.product.party.name=:name";

	@Transactional
	public InventoryPojo insert(InventoryPojo b) {
		em().persist(b);
		return b;
	}           


	public InventoryPojo selectByGlobalSkuId(Long globalSkuId) {
		TypedQuery<InventoryPojo> query = getQuery(selectByGlobalSkuId, InventoryPojo.class);
		query.setParameter("globalSkuId", globalSkuId);
		return getSingle(query);
	}

	public List<InventoryPojo> selectAll() {
		TypedQuery<InventoryPojo> query = getQuery(selectAll, InventoryPojo.class);
		return query.getResultList();
	}

	public List<InventoryPojo> selectByClientName(String name) {
		TypedQuery<InventoryPojo> query = getQuery(selectByClientName, InventoryPojo.class);
		query.setParameter("name", name);
		return query.getResultList();
	}
	
	
}

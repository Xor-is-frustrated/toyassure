package com.increff.assure.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.increff.assure.pojo.InventoryPojo;
import org.springframework.stereotype.Repository;

import com.increff.assure.pojo.ProductPojo;

@Repository
public class InventoryDao extends AbstractDao { 

	private static String selectById = "select p from InventoryPojo p where id=:id";
	private static String selectByProduct = "select p from InventoryPojo p where p.product=:product";
	private static String selectAll = "select p from InventoryPojo p";
	
	@Transactional
	public InventoryPojo insert(InventoryPojo b) {
		em().persist(b);
		return b;
	}           

	public InventoryPojo select(Long id) {
		TypedQuery<InventoryPojo> query = getQuery(selectById, InventoryPojo.class);
		query.setParameter("id", id);
		return getSingle(query);
	}

	public InventoryPojo selectByProduct(ProductPojo product) {
		TypedQuery<InventoryPojo> query = getQuery(selectByProduct, InventoryPojo.class);
		query.setParameter("product", product);
		return getSingle(query);
	}

	public List<InventoryPojo> selectAll() {
		TypedQuery<InventoryPojo> query = getQuery(selectAll, InventoryPojo.class);
		return query.getResultList();
	}
	
	
}

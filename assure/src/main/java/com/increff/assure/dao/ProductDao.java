package com.increff.assure.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.increff.assure.pojo.PartyPojo;
import com.increff.assure.pojo.ProductPojo;

@Repository
public class ProductDao extends AbstractDao {

	private static String selectById = "select p from ProductPojo p where p.globalSkuId=:globalSkuId";
	private static String selectAll = "select p from ProductPojo p order by p.globalSkuId";
	private static String selectByClientName = "select p from ProductPojo p where p.party.name=:name";

	private static String selectByClientIdAndClientSkuId = "select p from ProductPojo p where p.clientSkuId=:clientSkuId and p.party.id=:clientId";
	private static String selectByClientSkuId = "select p from ProductPojo p where p.clientSkuId=:clientSkuId ";

	@Transactional
	public ProductPojo insert(ProductPojo c) {
		em().persist(c);
		return c;
	}

	public ProductPojo select(Long globalSkuId) {
		TypedQuery<ProductPojo> query = getQuery(selectById, ProductPojo.class);
		query.setParameter("globalSkuId", globalSkuId);
		return getSingle(query);
	}

	public List<ProductPojo> selectAll() {
		TypedQuery<ProductPojo> query = getQuery(selectAll, ProductPojo.class);
		return query.getResultList();
	}

	public List<ProductPojo> selectByClientName(String name) {
		TypedQuery<ProductPojo> query = getQuery(selectByClientName, ProductPojo.class);
		query.setParameter("name", name);
		return query.getResultList();
	}

	public List<ProductPojo> selectByClientSkuId(String clientSkuId) {
		TypedQuery<ProductPojo> query = getQuery(selectByClientSkuId, ProductPojo.class);
		query.setParameter("clientSkuId", clientSkuId);
		return query.getResultList();
	}

	public ProductPojo selectByClientIdAndClientSkuId(String clientSkuId, Long clientId) {
		TypedQuery<ProductPojo> query = getQuery(selectByClientIdAndClientSkuId, ProductPojo.class);
		query.setParameter("clientSkuId", clientSkuId);
		query.setParameter("clientId", clientId);
		return getSingle(query);
	}

}

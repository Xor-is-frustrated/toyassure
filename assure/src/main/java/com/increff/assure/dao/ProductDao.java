package com.increff.assure.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.increff.assure.pojo.ClientPojo;
import com.increff.assure.pojo.ProductPojo;

@Repository
public class ProductDao extends AbstractDao {

	private static String selectById = "select p from ProductPojo p where p.globalSkuId=:id";
	private static String selectAll = "select p from ProductPojo p order by p.globalSkuId";
	private static String selectByClientAndClientSkuId = "select p from ProductPojo p where p.clientSkuId=:clientSkuId and p.client=:client";
	private static String selectByClientSkuId = "select p from ProductPojo p where p.clientSkuId=:clientSkuId ";

	@Transactional
	public ProductPojo insert(ProductPojo c) {
		em().persist(c);
		return c;
	}

	public ProductPojo select(Long id) {
		TypedQuery<ProductPojo> query = getQuery(selectById, ProductPojo.class);
		query.setParameter("id", id);
		return getSingle(query);
	}

	public List<ProductPojo> selectAll() {
		TypedQuery<ProductPojo> query = getQuery(selectAll, ProductPojo.class);
		return query.getResultList();
	}

	public List<ProductPojo> selectByClientSkuId(String clientSkuId) {
		TypedQuery<ProductPojo> query = getQuery(selectByClientSkuId, ProductPojo.class);
		query.setParameter("clientSkuId", clientSkuId);
		return query.getResultList();
	}

	public ProductPojo selectByClientAndClientSkuId(String clientSkuId, ClientPojo client) {
		TypedQuery<ProductPojo> query = getQuery(selectByClientAndClientSkuId, ProductPojo.class);
		query.setParameter("clientSkuId", clientSkuId);
		query.setParameter("client", client);
		return getSingle(query);
	}

}
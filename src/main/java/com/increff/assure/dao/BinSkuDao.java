package com.increff.assure.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.increff.assure.pojo.BinPojo;
import com.increff.assure.pojo.BinSkuPojo;
import com.increff.assure.pojo.ProductPojo;

@Repository
public class BinSkuDao extends AbstractDao{
	
	private static String selectById = "select p from BinSkuPojo p where p.id=:id";
	private static String selectAll = "select p from BinSkuPojo p order by p.id";
	private static String selectByBinAndProduct = "select p from BinSkuPojo p where p.product=:product and p.bin=:bin";
	
	@Transactional
	public BinSkuPojo insert(BinSkuPojo c) {
		em().persist(c);
		return c;
	}
	
	public BinSkuPojo select(Long id) {
		TypedQuery<BinSkuPojo> query = getQuery(selectById, BinSkuPojo.class);
		query.setParameter("id", id);
		return getSingle(query);
	}
	
	public List<BinSkuPojo> selectAll() {
		TypedQuery<BinSkuPojo> query = getQuery(selectAll, BinSkuPojo.class);
		return query.getResultList();
	}
	
	public BinSkuPojo selectByBinAndProduct(BinPojo bin, ProductPojo product) {
		TypedQuery<BinSkuPojo> query = getQuery(selectByBinAndProduct, BinSkuPojo.class);
		query.setParameter("bin", bin);
		query.setParameter("product", product);
		return getSingle(query);
	}
	

}

package com.increff.assure.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.increff.assure.pojo.BinSkuPojo;
import org.springframework.stereotype.Repository;

@Repository
public class BinSkuDao extends AbstractDao{
	
	private static String selectById = "select p from BinSkuPojo p where p.id=:id";
	private static String selectAll = "select p from BinSkuPojo p order by p.id";
	private static String selectByBinIdAndGlobalSkuId = "select p from BinSkuPojo p where p.product.globalSkuId=:globalSkuId and p.bin.id=:binId";
	private static String selectByGlobalSKuId = "select p from BinSkuPojo p where p.product.globalSkuId=:globalSkuId";
	
	  
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
	
	public List<BinSkuPojo> selectByGlobalSkuId(Long globalSkuId) {
		TypedQuery<BinSkuPojo> query = getQuery(selectByGlobalSKuId, BinSkuPojo.class);
		query.setParameter("globalSkuId", globalSkuId);
		return query.getResultList();
	}
	
	public BinSkuPojo selectByBinIdAndGlobalSkuId(Long binId, Long globalSkuId) {
		TypedQuery<BinSkuPojo> query = getQuery(selectByBinIdAndGlobalSkuId, BinSkuPojo.class);
		query.setParameter("binId", binId);
		query.setParameter("globalSkuId", globalSkuId);
		return getSingle(query);
	}
	
	
	

}

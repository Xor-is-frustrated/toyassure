package com.increff.assure.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.increff.assure.pojo.BinPojo;

@Repository
public class BinDao extends AbstractDao{
	
	private static String selectById = "select p from BinPojo p where id=:id";
	private static String selectAll = "select p from BinPojo p order by p.id";
	
	@Transactional
	public BinPojo insert(BinPojo c) {
		em().persist(c);
		return c;
	}

	public BinPojo select(Long id) {
		TypedQuery<BinPojo> query = getQuery(selectById, BinPojo.class);
		query.setParameter("id", id);
		return getSingle(query);
	}

	public List<BinPojo> selectAll() {
		TypedQuery<BinPojo> query = getQuery(selectAll, BinPojo.class);
		return query.getResultList();
	}
}

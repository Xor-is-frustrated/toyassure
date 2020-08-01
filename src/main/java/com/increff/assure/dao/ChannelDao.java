package com.increff.assure.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.increff.assure.pojo.ChannelPojo;

@Repository
public class ChannelDao extends AbstractDao{
	
	private static String selectById = "select p from ChannelPojo p where id=:id";
	private static String selectByName = "select p from ChannelPojo p where p.name=:name";
	private static String selectAll = "select p from ChannelPojo p order by p.id";
	
	@Transactional
	public ChannelPojo insert(ChannelPojo c) {
		em().persist(c);
		return c;
	}

	public ChannelPojo select(Long id) {
		TypedQuery<ChannelPojo> query = getQuery(selectById, ChannelPojo.class);
		query.setParameter("id", id);
		return getSingle(query);
	}
	
	public ChannelPojo selectByName(String name) {
		TypedQuery<ChannelPojo> query = getQuery(selectByName, ChannelPojo.class);
		query.setParameter("name", name);
		return getSingle(query);
	}
	
	public List<ChannelPojo> selectAll() {
		TypedQuery<ChannelPojo> query = getQuery(selectAll, ChannelPojo.class);
		return query.getResultList();
	}

}

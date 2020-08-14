package com.increff.assure.dao;

import com.increff.assure.pojo.ChannelPojo;
import com.increff.commons.data.ChannelData;
import com.increff.commons.enums.InvoiceType;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class ChannelDao extends AbstractDao{
	
	private static String selectById = "select p from ChannelPojo p where id=:id";
	private static String selectByName = "select p from ChannelPojo p where p.name=:name";
	private static String selectAll = "select p from ChannelPojo p order by p.id";
	private static String selectByType = "select p from ChannelPojo p where p.type=:type";

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

    public List<ChannelPojo> selectByType(InvoiceType type) {
		TypedQuery<ChannelPojo> query = getQuery(selectByType, ChannelPojo.class);
		query.setParameter("type", type);;
		return query.getResultList();

    }
}

package com.increff.assure.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.increff.assure.pojo.OrderPojo;

@Repository
public class OrderDao extends AbstractDao {

	private static String selectById = "select p from OrderPojo p where id=:id";
	private static String selectByChannelOrderId = "select p from OrderPojo p where p.channelOrderId=:channelOrderId";
	private static String selectAll = "select p from OrderPojo p order by p.id";
	private static String selectInternalOrders = "select p from OrderPojo p where p.channel.name=:internal order by p.id";


	@Transactional
	public OrderPojo insert(OrderPojo c) {
		em().persist(c);
		return c;
	}

	public OrderPojo select(Long id) {
		TypedQuery<OrderPojo> query = getQuery(selectById, OrderPojo.class);
		query.setParameter("id", id);
		return getSingle(query);
	}
	
	public OrderPojo selectByChannelOrderId(String channelOrderId) {
		TypedQuery<OrderPojo> query = getQuery(selectByChannelOrderId, OrderPojo.class);
		query.setParameter("channelOrderId", channelOrderId);
		return getSingle(query);
	}

	public List<OrderPojo> selectAll() {
		TypedQuery<OrderPojo> query = getQuery(selectAll, OrderPojo.class);
		
		return query.getResultList();
	}
 
	public List<OrderPojo> selectInternalOrders() {
		TypedQuery<OrderPojo> query = getQuery(selectInternalOrders, OrderPojo.class);
		query.setParameter("internal", "INTERNAL");       
		return query.getResultList();
	}

}

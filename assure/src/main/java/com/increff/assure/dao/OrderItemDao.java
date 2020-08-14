package com.increff.assure.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.increff.assure.pojo.OrderItemPojo;
import org.springframework.stereotype.Repository;

@Repository
public class OrderItemDao extends AbstractDao {  

	private static String selectById = "select p from OrderItemPojo p where id=:id";
	private static String selectByOrderId = "select p from OrderItemPojo p where p.order.id=:orderId order by p.id";
	private static String selectAll = "select p from OrderItemPojo p order by p.id";
	private static String selectByOrderIdAndGlobalSkuId = "select p from OrderItemPojo p where p.order.id=:orderId and p.product.globalSkuId=:globalSkuId";
	private static String deleteById = "delete from OrderItemPojo p where id=:id";
	
	@Transactional
	public OrderItemPojo insert(OrderItemPojo c) {
		em().persist(c);
		return c;
	}

	public OrderItemPojo select(Long id) {
		TypedQuery<OrderItemPojo> query = getQuery(selectById, OrderItemPojo.class);
		query.setParameter("id", id);
		return getSingle(query);
	}
	
	public List<OrderItemPojo> selectByOrderId(Long orderId) {
		TypedQuery<OrderItemPojo> query = getQuery(selectByOrderId, OrderItemPojo.class);
		query.setParameter("orderId", orderId);
		return query.getResultList();
	}
	
	public OrderItemPojo selectByOrderIdAndGlobalSkuId(Long orderId, Long globalSkuId) {
		TypedQuery<OrderItemPojo> query = getQuery(selectByOrderIdAndGlobalSkuId, OrderItemPojo.class);
		query.setParameter("orderId", orderId);
		query.setParameter("globalSkuId", globalSkuId);
		return getSingle(query);
	}

	public List<OrderItemPojo> selectAll() {
		TypedQuery<OrderItemPojo> query = getQuery(selectAll, OrderItemPojo.class);
		return query.getResultList();
	}
	
	public Long delete(Long id) {
		Query query = em().createQuery(deleteById);
		query.setParameter("id", id);
		return Long.valueOf(query.executeUpdate());
	}

}

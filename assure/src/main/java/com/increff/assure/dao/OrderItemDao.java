package com.increff.assure.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.increff.assure.pojo.OrderItemPojo;
import org.springframework.stereotype.Repository;

import com.increff.assure.pojo.OrderPojo;
import com.increff.assure.pojo.ProductPojo;

@Repository
public class OrderItemDao extends AbstractDao {  

	private static String selectById = "select p from OrderItemPojo p where id=:id";
	private static String selectByOrder = "select p from OrderItemPojo p where p.order=:order order by p.id";
	private static String selectAll = "select p from OrderItemPojo p order by p.id";
	private static String selectByOrderAndProduct = "select p from OrderItemPojo p where p.order=:order and p.product=:product";
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
	
	public List<OrderItemPojo> selectByOrder(OrderPojo order) {
		TypedQuery<OrderItemPojo> query = getQuery(selectByOrder, OrderItemPojo.class);
		query.setParameter("order", order);
		return query.getResultList();
	}
	
	public OrderItemPojo selectByOrderAndProduct(OrderPojo order, ProductPojo product) {
		TypedQuery<OrderItemPojo> query = getQuery(selectByOrderAndProduct, OrderItemPojo.class);
		query.setParameter("order", order);
		query.setParameter("product", product);
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

package com.increff.assure.dao;

import com.increff.assure.pojo.OrderPojo;
import com.increff.commons.enums.OrderStatus;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class OrderDao extends AbstractDao {

    private static String selectById = "select p from OrderPojo p where id=:id";
    private static String selectByChannelOrderId = "select p from OrderPojo p where p.channelOrderId=:channelOrderId";
    private static String selectAll = "select p from OrderPojo p order by p.id";
    private static String selectInternalOrders = "select p from OrderPojo p where p.channelId =:internal order by p.id";
    private static String selectExternalOrders = "select p from OrderPojo p where p.channelId !=:internal order by p.id";
    private static String deleteById = "delete from OrderPojo p where p.id=:id";
    private static String selectByChannelId = "select p from OrderPojo p where p.channelId=:channelId";
    private static String selectByStatus = "select p from OrderPojo p where p.status=:status";
    private static String selectByParams = "select p from OrderPojo p where p.status=:status and p" +
            ".channelId=:channelId";
    private static String selectByParams1 = "select p from OrderPojo p where p.status=:status and p" +
            ".channelId!=:channelId";


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

    public List<OrderPojo> selectByStatus(OrderStatus status) {
        TypedQuery<OrderPojo> query = getQuery(selectByStatus, OrderPojo.class);
        query.setParameter("status", status);
        return query.getResultList();
    }

    public List<OrderPojo> selectByChannelId(Long id) {
        TypedQuery<OrderPojo> query = getQuery(selectByChannelId, OrderPojo.class);
        query.setParameter("channelId", id);
        return query.getResultList();
    }

    public List<OrderPojo> selectByParams(Long id, OrderStatus status) {
        TypedQuery<OrderPojo> query = getQuery(selectByParams, OrderPojo.class);
        query.setParameter("channelId", id);
        query.setParameter("status", status);
        return query.getResultList();
    }



    public List<OrderPojo> selectExternalOrders(Long id) {
        TypedQuery<OrderPojo> query = getQuery(selectExternalOrders, OrderPojo.class);
        query.setParameter("internal", id);
        return query.getResultList();
    }

    public void delete(Long id) {
        Query query = em().createQuery(deleteById);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    public List<OrderPojo> selectByStatusExternal(OrderStatus status,Long id) {
        TypedQuery<OrderPojo> query = getQuery(selectByParams1, OrderPojo.class);
        query.setParameter("channelId", id);
        query.setParameter("status", status);
        return query.getResultList();
    }
}

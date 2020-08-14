package com.increff.assure.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.increff.assure.pojo.PartyPojo;
import com.increff.commons.enums.PartyType;
import org.springframework.stereotype.Repository;


@Repository
public class PartyDao extends AbstractDao{
	
	private static String selectById = "select p from PartyPojo p where id=:id";
	private static String selectByName = "select p from PartyPojo p where p.name=:name";
	private static String selectAll = "select p from PartyPojo p order by p.id";
	private static String selectAllClients="select p from PartyPojo p where p.type=:type order by p.id";
	private static String selectAllCustomers="select p from PartyPojo p where p.type=:type order by p.id";

	@Transactional
	public PartyPojo insert(PartyPojo c) {
		em().persist(c);
		return c;
	}

	public PartyPojo select(Long id) {
		TypedQuery<PartyPojo> query = getQuery(selectById, PartyPojo.class);
		query.setParameter("id", id);
		return getSingle(query);
	}
	
	public PartyPojo selectByName(String name) {
		TypedQuery<PartyPojo> query = getQuery(selectByName, PartyPojo.class);
		query.setParameter("name", name);
		return getSingle(query);
	}
	
	public List<PartyPojo> selectAll() {
		TypedQuery<PartyPojo> query = getQuery(selectAll, PartyPojo.class);
		return query.getResultList();
	}

	public List<PartyPojo> selectAllClients() {
		TypedQuery<PartyPojo> query = getQuery(selectAllClients, PartyPojo.class);
		query.setParameter("type", PartyType.CLIENT);
		return query.getResultList();
	}

	public List<PartyPojo> selectAllCustomers() {
		TypedQuery<PartyPojo> query = getQuery(selectAllCustomers, PartyPojo.class);
		query.setParameter("type", PartyType.CUSTOMER);
		return query.getResultList();
	}

	
	
	
}

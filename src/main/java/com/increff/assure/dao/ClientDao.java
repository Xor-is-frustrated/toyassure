package com.increff.assure.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.increff.assure.pojo.ClientPojo;


@Repository
public class ClientDao extends AbstractDao{
	
	private static String selectById = "select p from ClientPojo p where id=:id";
	private static String selectAll = "select p from ClientPojo p order by p.id";
	
	@Transactional
	public ClientPojo insert(ClientPojo c) {
		em().persist(c);
		return c;
	}

	public ClientPojo select(Long id) {
		TypedQuery<ClientPojo> query = getQuery(selectById, ClientPojo.class);
		query.setParameter("id", id);
		return getSingle(query);
	}
	
	public List<ClientPojo> selectAll() {
		TypedQuery<ClientPojo> query = getQuery(selectAll, ClientPojo.class);
		return query.getResultList();
	}

	
	
	
}

package com.increff.assure.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.increff.assure.dao.ChannelDao;
import com.increff.assure.pojo.ChannelPojo;
import com.increff.assure.pojo.ChannelType;

@Service
public class ChannelService extends AbstractService {

	@Autowired
	private ChannelDao dao;

	@EventListener(ContextRefreshedEvent.class)
	@Transactional(rollbackFor = ApiException.class)
	public void addDefaultChannel() throws ApiException {
		ChannelPojo existing = dao.selectByName("INTERNAL");
		if (existing == null) {
			ChannelPojo pojo = new ChannelPojo();
			pojo.setName("INTERNAL");
			pojo.setType(ChannelType.SELF);
			dao.insert(pojo);
		}

	}

	@Transactional(rollbackFor = ApiException.class)
	public ChannelPojo add(ChannelPojo p) throws ApiException {

		checkZero(p.getName().length(), "Name cannot be empty.");
		ChannelPojo existing = dao.selectByName(p.getName());
		checkNull(existing, "Name already exists.");

		ChannelPojo channelPojo = dao.insert(p);
		return channelPojo;
	}

	@Transactional(readOnly = true)
	public ChannelPojo get(Long id) throws ApiException {
		ChannelPojo p = dao.select(id);
		checkNotNull(p, "Channel ID does not exist");
		return p;
	}

	@Transactional(readOnly = true)
	public ChannelPojo getByName(String name) throws ApiException {
		ChannelPojo p = dao.selectByName(name);
		checkNotNull(p, "Channel Name does not exist");
		return p;
	}

	@Transactional(readOnly = true)
	public List<ChannelPojo> getAll() {
		return dao.selectAll();
	}

	@Transactional(rollbackFor = ApiException.class)
	public ChannelPojo update(Long id, ChannelPojo pojo) throws ApiException {
		checkZero(pojo.getName().length(), "Name cannot be empty.");

		ChannelPojo ex = dao.selectByName(pojo.getName());
		if (ex != null && ex.getId() != id) {
			throw new ApiException("Name already exists");
		}

		ChannelPojo p = dao.select(id);
		checkNotNull(p, "Client ID does not exist");
		if (p.getName().equals("INTERNAL")) {
			throw new ApiException("INTERNAL Name cannot be changed");
		}
		p.setName(pojo.getName());
		return p;
	}

}

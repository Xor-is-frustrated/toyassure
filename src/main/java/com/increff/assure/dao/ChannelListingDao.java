package com.increff.assure.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.increff.assure.pojo.ChannelListingPojo;
import com.increff.assure.pojo.ChannelPojo;

@Repository
public class ChannelListingDao extends AbstractDao {

	private static String selectById = "select p from ChannelListingPojo p where id=:id";
	private static String selectAll = "select p from ChannelListingPojo p order by p.id";
	private static String selectByChannelSkuIdAndChannel = "select p from ChannelListingPojo p where p.channelSkuId=:channelSkuId and p.channel=:channel";
 
 
	@Transactional
	public ChannelListingPojo insert(ChannelListingPojo c) {
		em().persist(c);
		return c;
	}

	public ChannelListingPojo select(Long id) {
		TypedQuery<ChannelListingPojo> query = getQuery(selectById, ChannelListingPojo.class);
		query.setParameter("id", id);
		return getSingle(query);
	}
	
	public ChannelListingPojo selectByChannelSkuIdAndChannel(String channelSkuId, ChannelPojo channel) {
		TypedQuery<ChannelListingPojo> query = getQuery(selectByChannelSkuIdAndChannel, ChannelListingPojo.class);
		query.setParameter("channel", channel);
		query.setParameter("channelSkuId", channelSkuId);
		return getSingle(query);
	}

	public List<ChannelListingPojo> selectAll() {
		TypedQuery<ChannelListingPojo> query = getQuery(selectAll, ChannelListingPojo.class);
		return query.getResultList();
	}

}

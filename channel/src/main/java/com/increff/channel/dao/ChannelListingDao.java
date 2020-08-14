package com.increff.channel.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.increff.channel.pojo.ChannelListingPojo;

@Repository
public class ChannelListingDao extends AbstractDao {

	private static String selectById = "select p from ChannelListingPojo p where id=:id";
	private static String selectAll = "select p from ChannelListingPojo p order by p.id";
	private static String selectByChannelName = "select p from ChannelListingPojo p where p.channelId=:id";
	private static String selectByChannelSkuIdAndChannelId = "select p from ChannelListingPojo p where p" +
			".channelSkuId=:channelSkuId and p.channelId=:channelId";
 
  
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
	
	public ChannelListingPojo selectByChannelSkuIdAndChannelId(String channelSkuId, Long channelId) {
		TypedQuery<ChannelListingPojo> query = getQuery(selectByChannelSkuIdAndChannelId, ChannelListingPojo.class);
		query.setParameter("channelId", channelId);
		query.setParameter("channelSkuId", channelSkuId);
		return getSingle(query);
	}

	public List<ChannelListingPojo> selectAll() {
		TypedQuery<ChannelListingPojo> query = getQuery(selectAll, ChannelListingPojo.class);
		return query.getResultList();
	}

    public List<ChannelListingPojo> selectByChannelId(Long id) {
		TypedQuery<ChannelListingPojo> query = getQuery(selectByChannelName, ChannelListingPojo.class);

		query.setParameter("id", id);
		return query.getResultList();
    }
}

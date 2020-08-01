package com.increff.assure.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.increff.assure.dao.ChannelDao;
import com.increff.assure.dao.ChannelListingDao;
import com.increff.assure.dao.ClientDao;
import com.increff.assure.dao.ProductDao;
import com.increff.assure.pojo.ChannelListingPojo;
import com.increff.assure.pojo.ChannelPojo;
import com.increff.assure.pojo.ClientPojo;
import com.increff.assure.pojo.ProductPojo;

@Service
public class ChannelListingService extends AbstractService {

	@Autowired
	private ChannelListingDao channelListingDao;

	@Autowired
	private ChannelDao channelDao;

	@Autowired
	private ProductDao productDao;

	@Autowired
	private ClientDao clientDao;

	@Transactional(rollbackFor = ApiException.class)
	public ChannelListingPojo add(ChannelListingPojo p) throws ApiException {

		checkZero(p.getChannelSkuId().length(), "ChannelSkuId cannot be empty.");

		ChannelPojo cp = channelDao.select(p.getChannel().getId());
		checkNotNull(cp, "Channel ID does not exist");

		ProductPojo product = productDao.select(p.getProduct().getGlobalSkuId());
		checkNotNull(product, "GlobalSkuID does not exist");

		ClientPojo client = clientDao.select(p.getClient().getId());
		checkNotNull(client, "ClientId does not exist");

		ChannelListingPojo cl = channelListingDao.selectByChannelSkuIdAndChannel(p.getChannelSkuId(), p.getChannel());
		checkNull(cl, "Combination already exists");
		return channelListingDao.insert(p);

	}

	@Transactional(readOnly = true)
	public ChannelListingPojo get(Long id) throws ApiException {
		ChannelListingPojo p = channelListingDao.select(id);
		checkNotNull(p, "Channe lListing ID does not exist");
		return p;
	}

	@Transactional(readOnly = true)
	public ChannelListingPojo getByChannelSkuIdAndChannel(String channelSkuId, ChannelPojo channel)
			throws ApiException {

		checkZero(channelSkuId.length(), "ChannelSkuId cannot be empty.");

		ChannelPojo cp = channelDao.select(channel.getId());
		checkNotNull(cp, "Channel ID does not exist");

		return channelListingDao.selectByChannelSkuIdAndChannel(channelSkuId, cp);
	}

	@Transactional(readOnly = true)
	public List<ChannelListingPojo> getAll() {
		return channelListingDao.selectAll();
	}

}

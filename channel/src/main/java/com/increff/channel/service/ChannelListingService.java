package com.increff.channel.service;

import java.util.List;

import com.increff.channel.assure.Client;
import com.increff.channel.assure.Product;
import com.increff.commons.data.ClientData;
import com.increff.commons.data.ProductData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.increff.channel.dao.ChannelDao;
import com.increff.channel.dao.ChannelListingDao;

import com.increff.channel.pojo.ChannelListingPojo;
import com.increff.channel.pojo.ChannelPojo;

import com.increff.commons.enums.ClientType;


@Service
public class ChannelListingService extends AbstractService {

	@Autowired
	private ChannelListingDao channelListingDao;

	@Autowired
	private ChannelDao channelDao;

	@Autowired
	private Product productDao;

	@Autowired
	private Client clientDao;

	@Transactional(rollbackFor = ApiException.class)
	public ChannelListingPojo add(ChannelListingPojo p) throws ApiException {

		checkZero(p.getChannelSkuId().length(), "ChannelSkuId cannot be empty.");

		ChannelPojo cp = channelDao.select(p.getChannel().getId());
		checkNotNull(cp, "Channel ID does not exist");

		ProductData product = productDao.getProductData(p.getGlobalSkuId());
		checkNotNull(product, "GlobalSkuID does not exist");

		ClientData client = clientDao.getClientData(p.getClientId());
		checkNotNull(client, "ClientId does not exist");
		if(client.getType()==ClientType.CUSTOMER) {
			throw new ApiException("Client Name does not exist");
		}
		ChannelListingPojo cl = channelListingDao.selectByChannelSkuIdAndChannel(p.getChannelSkuId(), p.getChannel());
		checkNull(cl, "Combination already exists");
		return channelListingDao.insert(p);

	}

	@Transactional(readOnly = true)
	public ChannelListingPojo get(Long id) throws ApiException {
		ChannelListingPojo p = channelListingDao.select(id);
		checkNotNull(p, "Channel Listing ID does not exist");
		return p;
	}

	@Transactional(readOnly = true)
	public ChannelListingPojo getByChannelSkuIdAndChannel(String channelSkuId, ChannelPojo channel)
			throws ApiException {

		checkZero(channelSkuId.length(), "ChannelSkuId cannot be empty.");

		ChannelPojo cp = channelDao.select(channel.getId());
		checkNotNull(cp, "Channel ID does not exist");

		ChannelListingPojo cl= channelListingDao.selectByChannelSkuIdAndChannel(channelSkuId, cp);
		checkNotNull(cl, "channelSkuId and channel name pair doesn't exist");
		return cl;
	}

	@Transactional(readOnly = true)
	public List<ChannelListingPojo> getAll() {
		return channelListingDao.selectAll();
	}

}

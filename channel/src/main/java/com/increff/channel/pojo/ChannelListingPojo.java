package com.increff.channel.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "assure_channel_listing", uniqueConstraints = @UniqueConstraint(columnNames = { "channel_id",
		"channel_sku_id" }))
public class ChannelListingPojo extends AbstractAuditable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "channel_sku_id", nullable = false)
	private String channelSkuId;


	@Column(name = "client_id", nullable = false)
	private Long clientId;


	@Column(name = "global_sku_id", nullable = false)
	private Long globalSkuId;

	@ManyToOne
	@JoinColumn(name = "channel_id", nullable = false)
	private ChannelPojo channel;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getChannelSkuId() {
		return channelSkuId;
	}

	public void setChannelSkuId(String channelSkuId) {
		this.channelSkuId = channelSkuId;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public Long getGlobalSkuId() {
		return globalSkuId;
	}

	public void setGlobalSkuId(Long globalSkuId) {
		this.globalSkuId = globalSkuId;
	}

	public ChannelPojo getChannel() {
		return channel;
	}

	public void setChannel(ChannelPojo channel) {
		this.channel = channel;
	}

}

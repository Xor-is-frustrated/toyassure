package com.increff.assure.pojo;

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
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "channelId","channelSkuId" }))
public class ChannelListingPojo  extends AbstractAuditable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String channelSkuId;
	
	@ManyToOne
	@JoinColumn(name = "clientId", nullable = false)
	private ClientPojo client;
	
	@ManyToOne
	@JoinColumn(name = "globalSkuId", nullable = false)
	private ProductPojo product;
	
	@ManyToOne
	@JoinColumn(name = "channelId", nullable = false)
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

	public ClientPojo getClient() {
		return client;
	} 

	public void setClient(ClientPojo client) {
		this.client = client;
	}

	public ProductPojo getProduct() {
		return product;
	}

	public void setProduct(ProductPojo product) {
		this.product = product;
	}

	public ChannelPojo getChannel() {
		return channel;
	}

	public void setChannel(ChannelPojo channel) {
		this.channel = channel;
	}
	
	

}

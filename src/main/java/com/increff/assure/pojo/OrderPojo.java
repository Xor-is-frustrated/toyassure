package com.increff.assure.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "channelOrderId"))
public class OrderPojo extends AbstractAuditable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "clientId", nullable = false)
	private ClientPojo client;

	@ManyToOne
	@JoinColumn(name = "customerId", nullable = false)
	private ClientPojo customer;

	@ManyToOne
	@JoinColumn(name = "channelId", nullable = false)
	private ChannelPojo channel;

	@Column(nullable = false)
	private String channelOrderId;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ClientPojo getClient() {
		return client;
	}

	public void setClient(ClientPojo client) {
		this.client = client;
	}

	public ClientPojo getCustomer() {
		return customer;
	}

	public void setCustomer(ClientPojo customer) {
		this.customer = customer;
	}

	public ChannelPojo getChannel() {
		return channel;
	}

	public void setChannel(ChannelPojo channel) {
		this.channel = channel;
	}

	public String getChannelOrderId() {
		return channelOrderId;
	}

	public void setChannelOrderId(String channelOrderId) {
		this.channelOrderId = channelOrderId;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

}

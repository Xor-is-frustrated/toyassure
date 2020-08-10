package com.increff.assure.pojo;

import com.increff.commons.enums.OrderStatus;

import javax.persistence.*;

@Entity
@Table(name = "assure_order", uniqueConstraints = @UniqueConstraint(columnNames = "channel_order_id"))
public class OrderPojo extends AbstractAuditable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "client_id", nullable = false)
	private ClientPojo client;

	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private ClientPojo customer;


	@Column(name = "channel_id", nullable = false)
	private Long channelId;

	@Column(name = "channel_order_id", nullable = false)
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

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
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

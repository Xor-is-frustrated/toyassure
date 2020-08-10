package com.increff.channel.pojo;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Date;

@MappedSuperclass
public class AbstractAuditable {

	private ZonedDateTime createdAt = ZonedDateTime.now();

	private ZonedDateTime modifiedAt = ZonedDateTime.now();

	@Version
	private Long version;

	public ZonedDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(ZonedDateTime createdAt) {
		this.createdAt =ZonedDateTime.now();
	}

	public ZonedDateTime getModifiedAt() {
		return modifiedAt;
	}

	@PreUpdate
	public void setModifiedAt() {
		this.modifiedAt = ZonedDateTime.now();
	}
}
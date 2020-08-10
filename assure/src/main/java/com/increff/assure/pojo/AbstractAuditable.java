package com.increff.assure.pojo;

import java.time.ZonedDateTime;


import javax.persistence.*;

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
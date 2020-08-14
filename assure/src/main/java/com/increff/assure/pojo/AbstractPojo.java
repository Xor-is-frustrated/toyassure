package com.increff.assure.pojo;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PreUpdate;
import javax.persistence.Version;
import java.time.ZonedDateTime;

@MappedSuperclass
public class AbstractPojo {

    @Column(nullable = false)
    private ZonedDateTime createdAt = ZonedDateTime.now();
    @Column(nullable = false)
    private ZonedDateTime modifiedAt = ZonedDateTime.now();

    @Version
    private Long version;

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = ZonedDateTime.now();
    }

    public ZonedDateTime getModifiedAt() {
        return modifiedAt;
    }

    @PreUpdate
    public void setModifiedAt() {
        this.modifiedAt = ZonedDateTime.now();
    }
}
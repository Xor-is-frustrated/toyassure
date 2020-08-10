package com.increff.assure.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="assure_bin")
public class BinPojo extends AbstractAuditable {

	@Id
	@Column(name="bin_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long binId;

	public Long getBinId() {
		return binId;
	}

	public void setBinId(Long binId) {
		this.binId = binId;
	}
	
	
	
}

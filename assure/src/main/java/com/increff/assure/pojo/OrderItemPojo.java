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
@Table(name="assure_order_items", uniqueConstraints = @UniqueConstraint(columnNames = { "order_id", "global_sku_id" }))
public class OrderItemPojo extends AbstractAuditable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "selling_price", nullable = false)
	private Double sellingPrice;
	@Column(name = "ordered_quantity", nullable = false)
	private Long orderedQuantity;
	@Column(name = "allocatedquantity", nullable = false)
	private Long allocatedQuantity;
	@Column(name = "fulfilled_quantity", nullable = false)
	private Long fulfilledQuantity;

	@ManyToOne
	@JoinColumn(name = "global_sku_id", nullable = false)
	private ProductPojo product;

	@ManyToOne
	@JoinColumn(name = "order_id", nullable = false)
	private OrderPojo order;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(Double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public Long getOrderedQuantity() {
		return orderedQuantity;
	}

	public void setOrderedQuantity(Long orderedQuantity) {
		this.orderedQuantity = orderedQuantity;
	}

	public Long getAllocatedQuantity() {
		return allocatedQuantity;
	}

	public void setAllocatedQuantity(Long allocatedQuantity) {
		this.allocatedQuantity = allocatedQuantity;
	}

	public Long getFulfilledQuantity() {
		return fulfilledQuantity;
	}

	public void setFulfilledQuantity(Long fulfilledQuantity) {
		this.fulfilledQuantity = fulfilledQuantity;
	}

	public ProductPojo getProduct() {
		return product;
	}

	public void setProduct(ProductPojo product) {
		this.product = product;
	}

	public OrderPojo getOrder() {
		return order;
	}

	public void setOrder(OrderPojo order) {
		this.order = order;
	}

}

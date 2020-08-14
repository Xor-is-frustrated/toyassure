package com.increff.assure.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "assure_order_items", uniqueConstraints = @UniqueConstraint(columnNames = {"order_id", "global_sku_id"}))
public class OrderItemPojo extends AbstractPojo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "selling_price", nullable = false)
    private Double sellingPrice;
    @Column(name = "ordered_quantity", nullable = false)
    private Long orderedQuantity;
    @Column(name = "allocated_quantity", nullable = false)
    private Long allocatedQuantity;
    @Column(name = "fulfilled_quantity", nullable = false)
    private Long fulfilledQuantity;

    @ManyToOne
    @JoinColumn(name = "global_sku_id", nullable = false)
    private ProductPojo product;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrderPojo order;


}

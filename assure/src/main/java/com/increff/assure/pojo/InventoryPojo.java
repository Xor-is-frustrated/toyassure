package com.increff.assure.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Setter
@Getter
@Table(name = "assure_inventory")
public class InventoryPojo extends AbstractPojo implements Serializable {

    @Column(name = "available_quantity", nullable = false)
    private Long availableQuantity;

    @Column(name = "allocated_quantity", nullable = false)
    private Long allocatedQuantity;

    @Column(name = "fulfilled_quantity", nullable = false)
    private Long fulfilledQuantity;

    @Id
    @OneToOne
    @JoinColumn(name = "global_sku_id", nullable = false)
    private ProductPojo product;


}

package com.increff.assure.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "assure_product", uniqueConstraints = @UniqueConstraint(columnNames = {"client_id", "client_sku_id"}))
public class ProductPojo extends AbstractPojo {

    @Id
    @Column(name = "global_sku_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long globalSkuId;

    @Column(name = "client_sku_id", nullable = false)
    private String clientSkuId;

    @Column(nullable = false)
    private String name;

    @Column(name = "brand_id", nullable = false)
    private String brandId;

    @Column(nullable = false)
    private Double mrp;

    private String description;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private PartyPojo party;

}

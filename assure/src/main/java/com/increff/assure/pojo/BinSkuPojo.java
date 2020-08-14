package com.increff.assure.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "assure_bin_sku", uniqueConstraints = @UniqueConstraint(columnNames = {"global_sku_id", "bin_id"}))
public class BinSkuPojo extends AbstractPojo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bin_id", nullable = false)
    private BinPojo bin;

    @ManyToOne
    @JoinColumn(name = "global_sku_id", nullable = false)
    private ProductPojo product;

    @Column(nullable = false)
    private Long quantity;


}

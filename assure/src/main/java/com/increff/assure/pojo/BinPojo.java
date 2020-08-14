package com.increff.assure.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "assure_bin")
@Setter
@Getter
public class BinPojo extends AbstractPojo {

    @Id
    @Column(name = "bin_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long binId;
}

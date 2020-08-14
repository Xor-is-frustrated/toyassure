package com.increff.assure.pojo;

import com.increff.commons.enums.InvoiceType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "assure_channel", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class ChannelPojo extends AbstractPojo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private InvoiceType type;


}

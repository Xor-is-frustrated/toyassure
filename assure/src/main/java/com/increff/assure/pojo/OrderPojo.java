package com.increff.assure.pojo;

import com.increff.commons.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "assure_order", uniqueConstraints = @UniqueConstraint(columnNames = {"channel_id", "channel_order_id"}))
public class OrderPojo extends AbstractPojo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private PartyPojo client;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private PartyPojo customer;


    @Column(name = "channel_id", nullable = false)
    private Long channelId;

    @Column(name = "channel_order_id", nullable = false)
    private String channelOrderId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;


}

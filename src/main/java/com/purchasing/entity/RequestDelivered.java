package com.purchasing.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author vanessa
 */
@Entity
@Table(name = "request_delivered", schema = "", catalog = "purchasing")
public class RequestDelivered {

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic
    @Column(name = "quantity", nullable = true, insertable = true, updatable = true, length = 45)
    private String quantity;

    @ManyToOne
    @JoinColumn(name = "order_request_id", referencedColumnName = "id", nullable = false)
    private OrderRequest orderRequest;

    @ManyToOne
    @JoinColumn(name = "reception_id", referencedColumnName = "id", nullable = false)
    private Reception reception;

}

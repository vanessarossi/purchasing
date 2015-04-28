package com.purchasing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

/**
 * @author vanessa
 */
@Entity
@Table(name = "request_delivered", schema = "", catalog = "purchasing")
public class RequestDelivered {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Digits(integer = 10, fraction = 2)
    @Column(name = "quantity")
    private Float quantity;

    @ManyToOne
    @JoinColumn(name = "order_request_id", referencedColumnName = "id", nullable = false)
    private OrderRequest orderRequest;

    @ManyToOne
    @JoinColumn(name = "reception_id", referencedColumnName = "id", nullable = false)
    private Reception reception;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public OrderRequest getOrderRequest() {
        return orderRequest;
    }

    public void setOrderRequest(OrderRequest orderRequest) {
        this.orderRequest = orderRequest;
    }

    public Reception getReception() {
        return reception;
    }

    public void setReception(Reception reception) {
        this.reception = reception;
    }
}

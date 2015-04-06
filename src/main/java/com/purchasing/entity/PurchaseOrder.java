package com.purchasing.entity;

import com.purchasing.enumerator.StatusEnum;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @author vanessa
 */
@Entity
@Table(name = "purchase_order", schema = "", catalog = "purchasing")
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "already_purchased")
    private Boolean alreadyPurchased;

    @NotNull
    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private StatusEnum status;

    @ManyToOne
    @JoinColumn(name = "approval_id", referencedColumnName = "id", nullable = false)
    private Approval approval;

    @OneToOne(mappedBy = "purchaseOrder")
    private DeliveryInformation deliveryInformation;

    @OneToOne
    @JoinColumn(name = "payment_information_id", referencedColumnName = "id")
    private PaymentInformation paymentInformation;

    @OneToOne(mappedBy = "purchaseOrder")
    private Reception reception;

    @OneToMany(mappedBy = "purchaseOrder")
    private List<AdditionalPurchaseOrder> additionalPurchaseOrders;

    @OneToMany(mappedBy = "purchaseOrder")
    private List<OrderRequest> orderRequests;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getAlreadyPurchased() {
        return alreadyPurchased;
    }

    public void setAlreadyPurchased(Boolean alreadyPurchased) {
        this.alreadyPurchased = alreadyPurchased;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Approval getApproval() {
        return approval;
    }

    public void setApproval(Approval approval) {
        this.approval = approval;
    }

    public DeliveryInformation getDeliveryInformation() {
        return deliveryInformation;
    }

    public void setDeliveryInformation(DeliveryInformation deliveryInformation) {
        this.deliveryInformation = deliveryInformation;
    }

    public PaymentInformation getPaymentInformation() {
        return paymentInformation;
    }

    public void setPaymentInformation(PaymentInformation paymentInformation) {
        this.paymentInformation = paymentInformation;
    }

    public Reception getReception() {
        return reception;
    }

    public void setReception(Reception reception) {
        this.reception = reception;
    }

    public List<AdditionalPurchaseOrder> getAdditionalPurchaseOrders() {
        return additionalPurchaseOrders;
    }

    public void setAdditionalPurchaseOrders(List<AdditionalPurchaseOrder> additionalPurchaseOrders) {
        this.additionalPurchaseOrders = additionalPurchaseOrders;
    }

    public List<OrderRequest> getOrderRequests() {
        return orderRequests;
    }

    public void setOrderRequests(List<OrderRequest> orderRequests) {
        this.orderRequests = orderRequests;
    }
}

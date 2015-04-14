package com.purchasing.entity;

import com.purchasing.enumerator.StatusEnum;

import javax.persistence.*;
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

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private StatusEnum status;

    @OneToOne
    @JoinColumn(name = "approval_id", referencedColumnName = "id", nullable = true)
    private Approval approval;

    @OneToOne
    @JoinColumn(name = "budget_id", referencedColumnName = "id")
    private Budget budget;

    @OneToOne
    @JoinColumn(name = "delivery_information_id", referencedColumnName = "id", nullable = true)
    private DeliveryInformation deliveryInformation;

    @OneToOne
    @JoinColumn(name = "payment_information_id", referencedColumnName = "id", nullable = true)
    private PaymentInformation paymentInformation;

    @OneToOne
    @JoinColumn(name = "reception_id" , referencedColumnName = "id", nullable = true)
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

    public Boolean isAlreadyPurchased() {
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

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
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

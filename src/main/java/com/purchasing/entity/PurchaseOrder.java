package com.purchasing.entity;

import com.purchasing.enumerator.StatusEnum;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author vanessa
 */
@Entity
@Table(name = "purchase_order")
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "date_generate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateGenerate;

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

    @Column(name = "justification_cancellation")
    private String justificationCancellation;

    @OneToOne
    @JoinColumn(name = "delivery_information_id", referencedColumnName = "id", nullable = true)
    private DeliveryInformation deliveryInformation;

    @OneToOne
    @JoinColumn(name = "payment_information_id", referencedColumnName = "id", nullable = true)
    private PaymentInformation paymentInformation;

    @OneToMany(mappedBy = "purchaseOrder")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<OrderRequest> orderRequests;

    @OneToMany(mappedBy = "purchaseOrder")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Reception> receptions;

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

    public Date getDateGenerate() {
        return dateGenerate;
    }

    public void setDateGenerate(Date dateGenerate) {
        this.dateGenerate = dateGenerate;
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

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    public String getJustificationCancellation() {
        return justificationCancellation;
    }

    public void setJustificationCancellation(String justificationCancellation) {
        this.justificationCancellation = justificationCancellation;
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

    public List<OrderRequest> getOrderRequests() {
        return orderRequests;
    }

    public void setOrderRequests(List<OrderRequest> orderRequests) {
        this.orderRequests = orderRequests;
    }

    public List<Reception> getReceptions() {
        return receptions;
    }

    public void setReceptions(List<Reception> receptions) {
        this.receptions = receptions;
    }
}

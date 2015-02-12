package com.purchasing.entity;

import com.purchasing.enumerator.StatusEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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

    @NotNull
    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private StatusEnum status;

    @ManyToOne
    @JoinColumn(name = "approval_id", referencedColumnName = "id", nullable = false)
    private Approval approval;

    @OneToOne(mappedBy = "purchaseOrder")
    private DeliveryInformation deliveryInformation;

    @OneToOne(mappedBy = "purchaseOrder")
    private PaymentInformation paymentInformation;

    @OneToOne(mappedBy = "purchaseOrder")
    private Reception reception;

    @OneToMany(mappedBy = "purchaseOrder")
    private List<AdditionalPurchaseOrder> additionalPurchaseOrders;

    @OneToMany(mappedBy = "purchaseOrder")
    private List<OrderRequest> orderRequests;

}

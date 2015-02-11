package com.purchasing.entity;

import com.purchasing.enumerator.StatusEnum;

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

    @Temporal(TemporalType.DATE)
    private Date date;

    @Enumerated(EnumType.ORDINAL)
    private StatusEnum status;

    @OneToMany(mappedBy = "purchaseOrder")
    private List<AdditionalPurchaseOrder> additionalPurchaseOrders;

    @OneToMany(mappedBy = "purchaseOrder")
    private List<OrderRequest> orderRequests;

    @ManyToOne
    @JoinColumn(name = "approval_id", referencedColumnName = "id", nullable = false)
    private Approval approval;

    @OneToOne(mappedBy = "purchaseOrder")
    private DeliveryInformation deliveryInformation;

    @OneToOne(mappedBy = "purchaseOrder")
    private PaymentInformation paymentInformation;
}

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

    @OneToOne
    @JoinColumn(name = "approval_id", referencedColumnName = "id")
    private Approval approval;

    @OneToOne
    @JoinColumn(name = "budget_id", referencedColumnName = "id")
    private Budget budget;

    @OneToOne
    @JoinColumn(name = "delivery_information_id", referencedColumnName = "id" )
    private DeliveryInformation deliveryInformation;

    @OneToOne
    @JoinColumn(name = "payment_information_id", referencedColumnName = "id")
    private PaymentInformation paymentInformation;

    @OneToOne
    @JoinColumn(name = "reception_id" , referencedColumnName = "id")
    private Reception reception;

    @OneToMany(mappedBy = "purchaseOrder")
    private List<AdditionalPurchaseOrder> additionalPurchaseOrders;

    @OneToMany(mappedBy = "purchaseOrder")
    private List<OrderRequest> orderRequests;


}

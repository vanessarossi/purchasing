package com.purchasing.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author vanessa
 */
@Entity
@Table(name = "additional_purchase_order", schema = "", catalog = "purchasing")
public class AdditionalPurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Length(min = 10, max = 20)
    @Column(name = "code")
    private String code;

    @OneToOne
    @JoinColumn(name = "reception_id", referencedColumnName = "id")
    private Reception reception;

    @ManyToOne
    @JoinColumn(name = "purchase_order_id", referencedColumnName = "id", nullable = false)
    private PurchaseOrder purchaseOrder;

    @OneToOne
    @JoinColumn(name = "payment_information_id", referencedColumnName = "id")
    private PaymentInformation paymentInformation;
}

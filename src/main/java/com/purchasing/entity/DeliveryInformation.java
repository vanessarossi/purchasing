package com.purchasing.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author vanessa
 */
@Entity
@Table(name = "delivery_information", schema = "", catalog = "purchasing")
public class DeliveryInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String street;

    private String neighborhood;

    private String number;

    private String receptorName;

    @OneToOne
    private PurchaseOrder purchaseOrder;
}

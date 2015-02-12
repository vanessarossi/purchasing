package com.purchasing.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
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

   @Length(min = 1, max = 150)
   @Column(name = "street")
    private String street;

    @Length(min = 1, max = 100)
    @Column(name = "neighborhood")
    private String neighborhood;

    @Length(min = 1, max = 45)
    @Column(name = "number")
    private String number;

    @Length(min = 1, max = 150)
    @Column(name = "receptor_name")
    private String receptorName;

    @OneToOne
    private PurchaseOrder purchaseOrder;
}
